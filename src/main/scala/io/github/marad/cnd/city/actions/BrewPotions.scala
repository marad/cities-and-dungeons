package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{Potion, City, CityAction}

object BrewPotions extends CityAction {
  val cost: Int = 15
  val duration: Float = 0
  val name = "Warzenie Mikstur"
  override def doAction(city: City): City = city.copy(
    hero = city.hero.copy(buffs = city.hero.buffs + Potion),
    preparation = city.preparation + 1
  )
}
