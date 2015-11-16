package io.github.marad.cnd

import org.widok._

object Main extends Application {

  val game = new Game {}

  val testing = Route("/", pages.GamePage(game))
  val settings = Route("/settings", pages.Settings(game))
  val battle = Route("/battle", pages.BattleTest(game))

  val routes = Set(testing, settings, battle)

  override def main(): Unit = {
    val router = Router(routes)
    router.listen()
  }
}

