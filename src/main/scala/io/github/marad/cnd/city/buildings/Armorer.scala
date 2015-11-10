package io.github.marad.cnd.city.buildings

import io.github.marad.cnd.city.{City, Building}

object Armorer extends Building {
  override def apply(city: City): City = city.copy(preparation = city.preparation + 1)
}
