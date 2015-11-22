package io.github.marad.cnd.city.actions

import io.github.marad.cnd.city.buildings.MagesGuild
import io.github.marad.cnd.city.{City, CityAction}
import org.widok.{View, Var}
import org.widok.bindings.Bootstrap._

object BuildMagesGuild extends CityAction {
  val cost = MagesGuild.cost
  val duration = MagesGuild.buildTime
  val name = "Wybuduj Gildię Magów"
  val levelInc = Var(1)

  override def turnStartEffect(city: City): City = city.copy(
    hero = city.hero.copy(level = city.hero.level + levelInc.get),
    buildings = city.buildings :+ MagesGuild
  )

  override val additionalSettings: View = Grid.Row(
    Grid.Column("Wzrost Poziomu").offset(Size.ExtraSmall, 4).column(Size.ExtraSmall, 2),
    Grid.Column(
      Input.Number().bind(levelInc.biMap(_.toString, _.toInt))
    ).column(Size.ExtraSmall, 2)
  )
}

