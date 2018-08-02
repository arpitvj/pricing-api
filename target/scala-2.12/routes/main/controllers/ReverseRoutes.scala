// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/avijayvargiy/code/play/pricing/conf/routes
// @DATE:Thu Aug 02 23:03:20 ICT 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers {

  // @LINE:1
  class ReversePriceController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def greetings(id:Int, name:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "greetings" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[Int]].unbind("id", id)), Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("name", name)))))
    }
  
    // @LINE:2
    def priceMovement(from:String, to:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "price/movement" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("from", from)), Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("to", to)))))
    }
  
  }


}
