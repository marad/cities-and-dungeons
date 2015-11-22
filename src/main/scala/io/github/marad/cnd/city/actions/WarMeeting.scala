package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{City, CityAction}
import org.widok._
import org.widok.bindings.Bootstrap._

object WarMeeting extends CityAction {
  val cost = Var(15)
  val duration = Var(0.5f)
  val name: String = "Narada Wojenna"
  val preparationIncrease = Var(3)

  override def turnStartEffect(city: City): City =
    city.copy(preparation = city.preparation + preparationIncrease.get)

  override val additionalSettings: View = Grid.Row(
    Grid.Column("Wzrost Gotowości").offset(Size.ExtraSmall, 4).column(Size.ExtraSmall, 2),
    Grid.Column(
      Input.Number().bind(preparationIncrease.biMap(_.toString, _.toInt))
    ).column(Size.ExtraSmall, 2)
  )
}
