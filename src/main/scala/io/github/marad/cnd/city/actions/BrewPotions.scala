package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{Potion, City, CityAction}
import org.widok.Var

object BrewPotions extends CityAction {
  val cost = Var(15)
  val duration = Var(0f)
  val name = "Warzenie Mikstur"
  override def doAction(city: City): City = {
    city.copy(
      hero = city.hero.copy(buffs = city.hero.buffs + Potion),
      preparation = city.preparation + 1
    )
  }
}
