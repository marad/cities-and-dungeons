package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{City, CityAction}
import org.widok.Var

object Taxes extends CityAction {
  val cost = Var(0)
  val duration = Var(1f)
  val name = "Podatki"

  override def turnStartEffect(city: City): City = city.copy(gold = city.gold + 5)
}
