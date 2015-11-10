package io.github.marad.cnd.city

import io.github.marad.cnd.Action

trait Building extends Action[City]

trait CityAction extends Action[City] {
  def cost: Int
  def duration: Float

  def doAction(city: City): City

  override def apply(city: City): City =
    if (city.gold >= cost) {
      doAction(city.copy(gold = city.gold - cost))
    } else {
      city
    }
}
