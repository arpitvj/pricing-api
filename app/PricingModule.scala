import controllers.PriceController
import play.api.mvc.ControllerComponents
import services.ServicesModule

trait PricingModule extends ServicesModule {

  import com.softwaremill.macwire._

  lazy val priceController = wire[PriceController]

  def controllerComponents: ControllerComponents
}
