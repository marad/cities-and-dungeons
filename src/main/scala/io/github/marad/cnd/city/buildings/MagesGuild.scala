package io.github.marad.cnd.city.buildings

import io.github.marad.cnd.Building
import io.github.marad.cnd.city.City

object MagesGuild extends Building[City] {
  val name = "Gildia Magów"
  val cost: Int = 20
  val buildTime: Float = 1f

  override def onTurnStart(city: City): City =
    city.copy(preparation = city.preparation + 2)
}
