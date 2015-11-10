package io.github.marad.cnd.city

import io.github.marad.cnd.Action

trait Building extends Action[City]

trait CityAction extends Action[City] {
  def cost: Int
}
