// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/avijayvargiy/code/play/pricing/conf/routes
// @DATE:Thu Aug 02 23:03:20 ICT 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReversePriceController PriceController = new controllers.ReversePriceController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReversePriceController PriceController = new controllers.javascript.ReversePriceController(RoutesPrefix.byNamePrefix());
  }

}
