import java.text.SimpleDateFormat

import akka.actor.Status.{Failure, Success}
import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.Json
import spray.json._

import scala.util.Try
import org.joda.time.{LocalDateTime => JodaLocalDateTime}
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat

package object models {

  /**
    *
    * Models
    */
  case class PriceInfo(price: Double, time: DateTime)
  case class PriceCurrencyInfo(base: String, currency: String, prices: Seq[PriceInfo])
  case class HistoricalData(data: PriceCurrencyInfo)


  /**
    * Parsers/Formatters
    */

  implicit val priceInfoFormats = Json.format[PriceInfo]
  implicit val priceCurrencyInfoFormats = Json.format[PriceCurrencyInfo]
  implicit val historicalDataFormats = Json.format[HistoricalData]

  object HistoricalDataProtocol extends DefaultJsonProtocol {

    implicit val priceInfoFormat = jsonFormat2(PriceInfo)
    implicit val priceCurrencyInfoFormat = jsonFormat3(PriceCurrencyInfo)

    implicit object DateTimeJsonFormat extends RootJsonFormat[org.joda.time.DateTime] {
      lazy val format = new java.text.SimpleDateFormat()
      def read(json: JsValue): DateTime = new DateTime()
      def write(date: DateTime) = JsString(new DateTime(date, DateTimeZone.UTC).withZone(DateTimeZone.forID("UTC")).getMillis.toString)
    }

    implicit object PriceInfoJsonFormat extends RootJsonFormat[PriceInfo] {
      def write(c: PriceInfo) = JsObject(
        "price" -> JsNumber(c.price),
        "time" -> JsString(c.time.toString(utils.DateUtil.DATE_TIME_FORMAT))
      )
      def read(value: JsValue) = {
        val jsonObj = value.asJsObject
        jsonObj.getFields("price", "time") match {
          case Seq(data, time) => {
            PriceInfo(data.convertTo[String].toDouble, utils.DateUtil.parseIsoDateString(time.convertTo[String]).get)
          }
          case _ => throw new DeserializationException("PriceInfo expected")
        }
      }
    }

    implicit object PriceCurrencyInfoJsonFormat extends RootJsonFormat[PriceCurrencyInfo] {
      def write(c: PriceCurrencyInfo) = JsObject(
        "base" -> JsString(c.base),
        "currency" -> JsString(c.currency),
        "prices" -> JsArray(JsObject("price" -> JsNumber(c.prices.head.price)))
      )
      def read(value: JsValue) = {
        val jsonObj = value.asJsObject
        jsonObj.getFields("base", "currency", "prices") match {
          case Seq(base, currency, prices) => PriceCurrencyInfo(base.convertTo[String], currency.convertTo[String], prices.convertTo[Seq[PriceInfo]])
          case _ => throw new DeserializationException("PriceCurrencyInfo expected")
        }
      }
    }

    implicit object HistoricalDataJsonFormat extends RootJsonFormat[HistoricalData] {
      def write(c: HistoricalData) = JsObject(
        "data" -> JsObject("base" -> JsString(c.data.base), "currency" -> JsString(c.data.currency))
      )
      def read(value: JsValue) = {
        val jsonObj = value.asJsObject
        jsonObj.getFields("data") match {
          case Seq(data) => HistoricalData(data.convertTo[PriceCurrencyInfo])
          case _ => throw new DeserializationException("HistoricalData expected")
        }
      }
    }
  }
}