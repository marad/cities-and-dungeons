package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{City, CityAction}

object WarMeeting extends CityAction {
  override def cost: Int = 15
  override def duration: Float = 0.5f
  override def name: String = "Narada Wojenna"

  override def doAction(city: City): City =
    city.copy(preparation = city.preparation + 3)
}
