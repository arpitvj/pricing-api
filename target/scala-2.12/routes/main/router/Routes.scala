// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/avijayvargiy/code/play/pricing/conf/routes
// @DATE:Thu Aug 02 23:03:20 ICT 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  PriceController_0: controllers.PriceController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    PriceController_0: controllers.PriceController
  ) = this(errorHandler, PriceController_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, PriceController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """greetings""", """controllers.PriceController.greetings(id:Int, name:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """price/movement""", """controllers.PriceController.priceMovement(from:String, to:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val controllers_PriceController_greetings0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("greetings")))
  )
  private[this] lazy val controllers_PriceController_greetings0_invoker = createInvoker(
    PriceController_0.greetings(fakeValue[Int], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PriceController",
      "greetings",
      Seq(classOf[Int], classOf[String]),
      "GET",
      this.prefix + """greetings""",
      """""",
      Seq()
    )
  )

  // @LINE:2
  private[this] lazy val controllers_PriceController_priceMovement1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("price/movement")))
  )
  private[this] lazy val controllers_PriceController_priceMovement1_invoker = createInvoker(
    PriceController_0.priceMovement(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PriceController",
      "priceMovement",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """price/movement""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case controllers_PriceController_greetings0_route(params@_) =>
      call(params.fromQuery[Int]("id", None), params.fromQuery[String]("name", None)) { (id, name) =>
        controllers_PriceController_greetings0_invoker.call(PriceController_0.greetings(id, name))
      }
  
    // @LINE:2
    case controllers_PriceController_priceMovement1_route(params@_) =>
      call(params.fromQuery[String]("from", None), params.fromQuery[String]("to", None)) { (from, to) =>
        controllers_PriceController_priceMovement1_invoker.call(PriceController_0.priceMovement(from, to))
      }
  }
}
