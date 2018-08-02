package controllers

import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Date

import models._
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}
import services.PricingService


class PriceController(pricingService: PricingService,
                        cc: ControllerComponents) extends AbstractController(cc) {



  def priceMovement(from: String, to: String) = Action {

    val fromDate = DateUtil.convertStringToDate(from)
    val toDate = DateUtil.convertStringToDate(to)

    val stream = new FileInputStream("data.json")
    val json: JsValue = try {  Json.parse(stream) } finally { stream.close() }
    val readVal: JsResult[PriceCurrencyInfo] = Json.fromJson[PriceCurrencyInfo](json)

    readVal match {
      case JsSuccess(r: PriceCurrencyInfo, path: JsPath) => Ok(Json.toJson(r))
      case e: JsError => Ok(Json.toJson("Failed"))
    }
  }


  object DateUtil {
    val DATE_FORMAT = "yyyy-MM-dd"

    def getDateAsString(d: Date): String = {
      val dateFormat = new SimpleDateFormat(DATE_FORMAT)
      dateFormat.format(d)
    }

    def convertStringToDate(s: String): Date = {
      val dateFormat = new SimpleDateFormat(DATE_FORMAT)
      dateFormat.parse(s)
    }

  }
}
