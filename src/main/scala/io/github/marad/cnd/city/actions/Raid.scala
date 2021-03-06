package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{City, CityAction}
import org.widok._
import org.widok.bindings.Bootstrap._

object Raid extends CityAction {
  val cost = Var(5)
  val duration = Var(0.5f)
  val name = "Rajd"
  val goldInc = Var(15)

  override def turnStartEffect(city: City): City = city.copy(gold = city.gold + goldInc.get)

  override val additionalSettings: View = Grid.Row(
    Grid.Column("Przychód").offset(Size.ExtraSmall, 4).column(Size.ExtraSmall, 2),
    Grid.Column(
      Input.Number().bind(goldInc.biMap(_.toString, _.toInt))
    ).column(Size.ExtraSmall, 2)
  )
}