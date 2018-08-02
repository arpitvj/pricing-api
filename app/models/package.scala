import play.api.libs.json.{JsPath, Json, Reads, Writes, OFormat}

package object models {






  //implicit val historicalDataFormat = Json.format[HistoricalData]


  /*val dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"

  implicit val jodaDateReads = Reads[DateTime](js =>
    js.validate[String].map[DateTime](dtString =>
      DateTime.parse(dtString, DateTimeFormat.forPattern(dateFormat))
    )
  )

  implicit val jodaDateWrites: Writes[DateTime] = new Writes[DateTime] {
    def writes(d: DateTime): JsValue = JsString(d.toString())
  }

  implicit val userWrites: Writes[PriceInfo] = (
    (JsPath \ "price").write[Double] and
      (JsPath \ "time").write[DateTime](jodaDateWrites)
    )(unlift(PriceInfo.unapply))

  implicit val priceInfo: Reads[PriceInfo] = (
    (JsPath \ "price").read[Double] and
      (JsPath \ "time").read[DateTime](jodaDateReads)
    )(PriceInfo.apply _)*/

  //implicit val aa: Format[PriceInfo] = Format(priceInfo, userWrites)

  import play.api.libs.functional.syntax._
  case class PriceInfo(price: Double)//, time: DateTime)
  implicit val locationReads: Reads[PriceInfo] =
    ((json \ "price").read[Double])(PriceInfo.apply _)

  implicit val locationWrites: Writes[PriceInfo] = (
    (JsPath \ "price").write[Double]
    )(unlift(PriceInfo.unapply))


  implicit val priceInfoWrite = Json.writes[PriceInfo]
  implicit val priceInfoRead = Json.reads[PriceInfo]
  implicit val aa: OFormat[PriceInfo] = Json.format[PriceInfo]

  //case class PriceInfo(price: Double)//, time: DateTime)

  implicit val priceWrite = Json.writes[PriceCurrencyInfo]
  implicit val priceRead = Json.reads[PriceCurrencyInfo]
  implicit val priceCurrencyInfo: OFormat[PriceCurrencyInfo] = Json.format[PriceCurrencyInfo]

  case class PriceCurrencyInfo(base: Option[String], currency: Option[String], prices: Seq[PriceInfo])


  implicit val historicalDataFormat: OFormat[HistoricalData] = Json.format[HistoricalData]
  case class HistoricalData(data: PriceCurrencyInfo)


  object HistoricalData {
    /*val dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    implicit val jodaDateReads = Reads[DateTime](js =>
      js.validate[String].map[DateTime](dtString =>
        DateTime.parse(dtString, DateTimeFormat.forPattern(dateFormat))
      )
    )

    val jodaDateWrites: Writes[DateTime] = new Writes[DateTime] {
      def writes(d: DateTime): JsValue = JsString(d.toString())
    }

    val userWrites: Writes[PriceInfo] = (
      (JsPath \ "price").write[Double] and
        (JsPath \ "time").write[DateTime](jodaDateWrites)
      )(unlift(PriceInfo.unapply))


    implicit val aa = Format(priceInfo, userWrites)

    implicit val priceInfo: Reads[PriceInfo] = (
      (JsPath \ "price").read[Double] and
        (JsPath \ "time").read[DateTime](jodaDateReads)
      )(PriceInfo.apply _)
*/



  }


}
