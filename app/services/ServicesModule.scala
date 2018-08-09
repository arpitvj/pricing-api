package services

trait ServicesModule {

  import com.softwaremill.macwire._


  /**
    * Wiring Services here. Used for Dependency Injection.
    */
  lazy val pricingService = wire[PricingServiceImpl]

}
