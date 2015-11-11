package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{City, CityAction}
import org.widok.Var

object WarMeeting extends CityAction {
  val cost = Var(15)
  val duration = Var(0.5f)
  val name: String = "Narada Wojenna"

  override def doAction(city: City): City =
    city.copy(preparation = city.preparation + 3)
}
