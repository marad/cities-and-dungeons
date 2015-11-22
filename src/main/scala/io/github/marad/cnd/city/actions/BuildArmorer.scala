package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.buildings.Armorer
import io.github.marad.cnd.city.{City, CityAction}
import org.widok.{View, Var}
import org.widok.bindings.Bootstrap._

object BuildArmorer extends CityAction {
  val cost = Armorer.cost
  val duration = Armorer.buildTime
  val name = "Wybuduj warsztat Płatnerza"
  val levelInc = Var(2)

  override def turnStartEffect(city: City): City = city.copy(
    hero = city.hero.copy(level = city.hero.level + levelInc.get),
    buildings = city.buildings :+ Armorer
  )

  override val additionalSettings: View = Grid.Row(
    Grid.Column("Wzrost Poziomu").offset(Size.ExtraSmall, 4).column(Size.ExtraSmall, 2),
    Grid.Column(
      Input.Number().bind(levelInc.biMap(_.toString, _.toInt))
    ).column(Size.ExtraSmall, 2)
  )
}
