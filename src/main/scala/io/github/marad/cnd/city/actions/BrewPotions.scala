package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.{Potion, City, CityAction}
import org.widok.{View, Var}
import org.widok.bindings.Bootstrap._

object BrewPotions extends CityAction {
  val cost = Var(15)
  val duration = Var(0f)
  val name = "Warzenie Mikstur"
  val preparationIncrease = Var(1)

  override def turnStartEffect(city: City): City = {
    city.copy(
      hero = city.hero.copy(buffs = city.hero.buffs + Potion),
      preparation = city.preparation + preparationIncrease.get
    )
  }

  override val additionalSettings: View = Grid.Row(
    Grid.Column("Wzrost Gotowości").offset(Size.ExtraSmall, 4).column(Size.ExtraSmall, 2),
    Grid.Column(
      Input.Number().bind(preparationIncrease.biMap(_.toString, _.toInt))
    ).column(Size.ExtraSmall, 2)
  )
}
