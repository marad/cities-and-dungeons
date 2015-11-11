package io.github.marad.cnd

import org.widok._

object Main extends Application {

  val testing = Route("/", pages.Testing)
  val settings = Route("/settings", pages.Settings)

  val routes = Set(testing, settings)

  override def main(): Unit = {
    val router = Router(routes)
    router.listen()
  }
}
