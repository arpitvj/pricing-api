// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/avijayvargiy/code/play/pricing/conf/routes
// @DATE:Thu Aug 02 23:03:20 ICT 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
