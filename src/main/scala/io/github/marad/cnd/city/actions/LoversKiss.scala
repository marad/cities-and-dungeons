package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{Kiss, City, CityAction}
import org.widok.Var

object LoversKiss extends CityAction {
  val cost = Var(0)
  val duration = Var(0.5f)
  val name = "Pocałunek Ukochanej"

  override def turnStartEffect(city: City): City =
    city.copy(hero = city.hero.copy(buffs = city.hero.buffs + Kiss))
}
