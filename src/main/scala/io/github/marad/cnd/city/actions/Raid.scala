package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{City, CityAction}

object Raid extends CityAction {
  val cost = 5
  val duration = 0.5f
  val name = "Rajd"

  override def doAction(city: City): City = city.copy(gold = city.gold + 15)
}