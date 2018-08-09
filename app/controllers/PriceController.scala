package controllers

import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import services.PricingService

class PriceController(pricingService: PricingService,
                        cc: ControllerComponents) extends AbstractController(cc) {

  def priceMovement(from: String, to: String) = Action {
    import models._
    import HistoricalDataProtocol._

    Ok(Json.toJson(pricingService.priceMovement(from, to)))
  }

  def averageMovement(from: String, to: String) = Action {
    import models._
    import HistoricalDataProtocol._

    Ok(Json.toJson(pricingService.averageMovement(from, to)))
  }

  def suggestion(days: Int) = Action {
    import models._
    import HistoricalDataProtocol._

    Ok(Json.toJson(pricingService.suggestion(days)))
  }
}
