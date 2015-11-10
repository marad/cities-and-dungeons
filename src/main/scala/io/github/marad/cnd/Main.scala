package io.github.marad.cnd

import org.widok._

object Main extends Application {

  val testing = Route("/", pages.Testing)

  val routes = Set(testing)

  override def main(): Unit = {
    val router = Router(routes)
    router.listen()
  }
}
