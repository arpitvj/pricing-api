import java.text.SimpleDateFormat
import java.util.Date

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

import scala.util.Try

package object utils {

  object DateUtil {
    val DATE_FORMAT = "yyyy-MM-dd"
    val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

    def getDateAsString(d: Date): String = {
      val dateFormat = new SimpleDateFormat(DATE_FORMAT)
      dateFormat.format(d)
    }

    def convertStringToDate(s: String): Date = {
      val dateFormat = new SimpleDateFormat(DATE_FORMAT)
      dateFormat.parse(s)
    }

    def parseIsoDateString(date: String): Option[DateTime] =
      Try{ DateTime.parse(date, DateTimeFormat.forPattern(DATE_TIME_FORMAT)) }.toOption
  }
}
