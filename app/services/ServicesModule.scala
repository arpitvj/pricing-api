package services

trait ServicesModule {

  import com.softwaremill.macwire._

  lazy val pricingService = wire[PricingService]

}
