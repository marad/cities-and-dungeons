package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{Kiss, City, CityAction}

object LoversKiss extends CityAction {
  val cost: Int = 0
  val duration: Float = 0.5f
  val name = "Pocałunek Ukochanej"

  override def doAction(city: City): City =
    city.copy(hero = city.hero.copy(buffs = city.hero.buffs + Kiss))
}
