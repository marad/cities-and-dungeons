package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.buildings.MagesGuild
import io.github.marad.cnd.city.{City, CityAction}
import org.widok.Var

object BuildMagesGuild extends CityAction {
  val cost = MagesGuild.cost
  val duration = MagesGuild.buildTime
  val name = "Wybuduj Gildię Magów"

  override def doAction(city: City): City = city.copy(
    hero = city.hero.copy(level = city.hero.level + 1),
    buildings = city.buildings :+ MagesGuild
  )
}

