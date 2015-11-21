package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{City, CityAction}
import org.widok.Var

object Raid extends CityAction {
  val cost = Var(5)
  val duration = Var(0.5f)
  val name = "Rajd"

  override def turnStartEffect(city: City): City = city.copy(gold = city.gold + 15)
}