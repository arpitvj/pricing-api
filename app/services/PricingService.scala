package services

import java.text.SimpleDateFormat
import java.util
import java.util.Date

import play.api.libs.json.Json
import spray.json.enrichString
import models._
import HistoricalDataProtocol._
import org.joda.time.DateTime

trait PricingService {

  def priceMovement(from: String, to: String): Seq[PriceInfo]

  def averageMovement(from: String, to: String): Double

  def suggestion(days: Int): String
}

class PricingServiceImpl extends PricingService {

  val input = scala.io.Source.fromFile("data.json")("UTF-8").mkString
  val jsonAst = input.parseJson
  val pricingData = jsonAst.convertTo[HistoricalData]

  def priceMovement(from: String, to: String): Seq[PriceInfo] = {

    val fromDate = utils.DateUtil.convertStringToDate(from).getTime
    val toDate = utils.DateUtil.convertStringToDate(to).getTime

    pricingData.data.prices.filter(t => (t.time.isAfter(fromDate) && t.time.isBefore(toDate)))
  }

  override def averageMovement(from: String, to: String): Double = {

    val fromDate = utils.DateUtil.convertStringToDate(from).getTime
    val toDate = utils.DateUtil.convertStringToDate(to).getTime

    val filteredObject = pricingData.data.prices.filter(t => (t.time.isAfter(fromDate) && t.time.isBefore(toDate)))
    (filteredObject.map(_.price).sum / filteredObject.size)
  }

  def suggestion(days: Int): String = {

    val fromDate = DateTime.now.minusDays(days)
    val toDate = DateTime.now
    val fromDateLong = toDate.minusDays(25)

    val previousDayFromDate = DateTime.now.minusDays(days+1)
    val previousDayToDate = DateTime.now.minusDays(1)
    val previousDateLong = previousDayToDate.minusDays(25)

    val input = scala.io.Source.fromFile("data.json")("UTF-8").mkString
    val jsonAst = input.parseJson
    val myObject: HistoricalData = jsonAst.convertTo[HistoricalData]


    val todaysShortSMA = calculateSMA(myObject.data.prices.filter(t => (t.time.isAfter(fromDate) && t.time.isBefore(toDate))))
    val todaysLongSMA = calculateSMA(myObject.data.prices.filter(t => (t.time.isAfter(fromDateLong) && t.time.isBefore(toDate))))

    val yesterdaysShortSMA = calculateSMA(myObject.data.prices.filter(t => (t.time.isAfter(previousDayFromDate) && t.time.isBefore(previousDayToDate))))
    val yesterdaysLongSMA = calculateSMA(myObject.data.prices.filter(t => (t.time.isAfter(previousDateLong) && t.time.isBefore(previousDayToDate))))


    // today's SMA = calculate (today - number of x days) SMA vs 25 days SMA
    // yesterday SMA = calculate (yesterday - number of x days) SMA vs (yesterday - 25 days) SMA
    // if (yesterday's calculate (yesterday - number of x days) < (yesterday - 25 days) SMA && today's (today - number of x days)SMA > (today's 25 days SMA)) => `BUY`
    // else if (yesterday's calculate (yesterday - number of x days) > (yesterday - 25 days) SMA && today's (today - number of x days)SMA < (today's 25 days SMA)) => `SELL`
    // else (yesterday's calculate (yesterday - number of x days) > (yesterday - 25 days) SMA && today's (today - number of x days)SMA > (today's 25 days SMA)) => `HOLD`

    val yesterdaysChange = yesterdaysShortSMA - yesterdaysLongSMA
    val todaysChange = todaysShortSMA - todaysLongSMA

    (yesterdaysChange, todaysChange) match {

      case (a,b) if (a < 0.0 && b > 0.0) => "BUY"
      case (a,b) if (a > 0.0 && b < 0.0) => "SELL"
      case _ => "HOLD"
    }
  }

  def calculateSMA(prices: Seq[PriceInfo]): Double = {
    prices.map(_.price).sum / prices.size
  }
}
