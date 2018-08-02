// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/avijayvargiy/code/play/pricing/conf/routes
// @DATE:Thu Aug 02 23:03:20 ICT 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers.javascript {

  // @LINE:1
  class ReversePriceController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def greetings: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PriceController.greetings",
      """
        function(id0,name1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "greetings" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[Int]].javascriptUnbind + """)("id", id0), (""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("name", name1)])})
        }
      """
    )
  
    // @LINE:2
    def priceMovement: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PriceController.priceMovement",
      """
        function(from0,to1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "price/movement" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("from", from0), (""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("to", to1)])})
        }
      """
    )
  
  }


}
