package io.github.marad.cnd.city.buildings

import io.github.marad.cnd.Building
import io.github.marad.cnd.city.City
import org.widok.Var

object Armorer extends Building[City] {
  val name = "Płatnerz"
  val cost: Var[Int] = Var(20)
  val buildTime: Var[Float] = Var(1f)

  override def onTurnStart(city: City): City =
    city.copy(preparation = city.preparation + 1)
}
