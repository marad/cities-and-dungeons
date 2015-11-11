package io.github.marad.cnd.city.buildings

import io.github.marad.cnd.Building
import io.github.marad.cnd.city.City

object Armorer extends Building[City] {
  val name = "Płatnerz"
  val cost: Int = 20
  val buildTime: Float = 1f
  //override def apply(city: City): City = city.copy(preparation = city.preparation + 1)

  override def onTurnStart(city: City): City =
    city.copy(preparation = city.preparation + 1)

}
