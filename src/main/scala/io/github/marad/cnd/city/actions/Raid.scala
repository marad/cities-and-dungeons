package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{City, CityAction}

class Raid extends CityAction {
  override def apply(city: City): City =
    city.copy(gold = city.gold + 10)
}
