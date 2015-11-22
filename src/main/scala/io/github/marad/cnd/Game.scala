package io.github.marad.cnd

import io.github.marad.cnd.city.City
import io.github.marad.cnd.core.{DungeonActions, CityActions}
import io.github.marad.cnd.core.dayCycle.DayCycleComponent
import io.github.marad.cnd.dungeon.Dungeon
import io.github.marad.cnd.widgets.Log
import org.widok._

import scala.util.Random

trait Game
  extends DayCycleComponent
  with CityActions
  with DungeonActions
{
  val log = new Log()
  val random = new Random()

  val city = Var(City())
  val dungeon = Var(Dungeon())

  def resetGame() = {
    city := City()
    dungeon := Dungeon()
  }
}
