package io.github.marad.cnd.city

import io.github.marad.cnd.Action
import org.widok.Var


trait CityAction extends Action[City] {
  protected def payForIt(city: City): City =
    if (city.gold >= cost.get)
      city.copy(gold = city.gold - cost.get)
    else city

  override def instantEffect(city: City): City = payForIt(city)
  override def turnStartEffect(city: City): City
}
