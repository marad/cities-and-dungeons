package io.github.marad.cnd.widgets

import io.github.marad.cnd.dungeon.Dungeon
import org.scalajs.dom.html.Element
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class DungeonView(dungeon: ReadChannel[Dungeon]) extends Widget[DungeonView] {

  val labelColumnSize = 4
  val valueColumnSize = 8

  val energyView = dungeon.map(_.energy)
  val crystalsView = dungeon.map(_.crystals)
  val strengthView = dungeon.map(_.strength)
  val buildingsView = BuildingsView(dungeon.map(_.buildings))

  override val rendered: Element = DOM.createElement("span", Seq(
    Grid.Row(Grid.Column(h1("Lochy")).column(Size.ExtraSmall, 12)),
    Grid.Row(
      Grid.Column("Energia:").column(Size.ExtraSmall, labelColumnSize),
      Grid.Column(energyView).column(Size.ExtraSmall, valueColumnSize)
    ),
    Grid.Row(
      Grid.Column("Kryształy Energii:").column(Size.ExtraSmall, labelColumnSize),
      Grid.Column(crystalsView).column(Size.ExtraSmall, valueColumnSize)
    ),
    Grid.Row(
      Grid.Column("Siła:").column(Size.ExtraSmall, labelColumnSize),
      Grid.Column(strengthView).column(Size.ExtraSmall, valueColumnSize)
    ),
    Grid.Row(
      Grid.Column("Budynki:").column(Size.ExtraSmall, labelColumnSize),
      Grid.Column(buildingsView).column(Size.ExtraSmall, valueColumnSize)
    )
  ))
}
