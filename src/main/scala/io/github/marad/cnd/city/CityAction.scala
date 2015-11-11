package io.github.marad.cnd.city

import io.github.marad.cnd.Action
import org.widok.Var


trait CityAction extends Action[City] {
  def doAction(city: City): City

  override def apply(city: City): City =
    if (city.gold >= cost.get) {
      doAction(city.copy(gold = city.gold - cost.get))
    } else {
      city
    }
}
