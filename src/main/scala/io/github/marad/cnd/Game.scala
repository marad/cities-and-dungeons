package io.github.marad.cnd

import io.github.marad.cnd.city.City
import io.github.marad.cnd.dungeon.Dungeon
import io.github.marad.cnd.widgets.Log
import org.widok._

import scala.util.Random

object Game {

  val city = Var(City())
  val dungeon = Var(Dungeon())

  val log = new Log()
  val random = new Random()

  def resetGame() = {
    city := City()
    dungeon := Dungeon()
  }
}
