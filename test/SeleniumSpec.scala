import org.scalatestplus.play._

class SeleniumSpec
  extends PlaySpec
  with BaseOneServerPerTest
  with OneBrowserPerTest
  with PricingApplicationFactory
  with HtmlUnitFactory {

  "SeleniumSpec" should {

    "work from within a browser" in {

      go to ("http://localhost:" + port)

      pageSource must include ("Your new application is ready.")
    }
  }
}
