package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{City, CityAction}

object Taxes extends CityAction {
  val cost: Int = 0
  val duration: Float = 1f
  val name = "Podatki"

  override def doAction(city: City): City = city.copy(gold = city.gold + 5)
}
