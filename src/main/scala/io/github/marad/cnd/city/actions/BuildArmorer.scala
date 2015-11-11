package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.buildings.Armorer
import io.github.marad.cnd.city.{City, CityAction}

object BuildArmorer extends CityAction {
  val cost: Int = 20
  val duration: Float = 1f
  val name = "Wybuduj warsztat Płatnerza"

  override def doAction(city: City): City = city.copy(
    hero = city.hero.copy(level = city.hero.level + 2),
    buildings = city.buildings :+ Armorer
  )
}
