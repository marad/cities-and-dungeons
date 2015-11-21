package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.buildings.Armorer
import io.github.marad.cnd.city.{City, CityAction}
import org.widok.Var

object BuildArmorer extends CityAction {
  val cost = Armorer.cost
  val duration = Armorer.buildTime
  val name = "Wybuduj warsztat Płatnerza"

  override def turnStartEffect(city: City): City = city.copy(
    hero = city.hero.copy(level = city.hero.level + 2),
    buildings = city.buildings :+ Armorer
  )
}
