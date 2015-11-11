package io.github.marad.cnd.widgets

import io.github.marad.cnd.city.City
import org.scalajs.dom.html.Element
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class CityView(city: Var[City]) extends Widget[CityView] {

  val labelColumnSize = 4
  val valueColumnSize = 8

  val goldView = city.map(_.gold)
  val preparationView = city.map(_.preparation)
  val heroView = HeroView(city.map(_.hero))
  val buildingsView = BuildingsView(city.map(_.buildings))

  override val rendered: Element = DOM.createElement("span", Seq(
    Grid.Row(Grid.Column(h1("Miasto")).column(Size.ExtraSmall, 12)),
    Grid.Row(
      Grid.Column("Złoto:").column(Size.ExtraSmall, labelColumnSize),
      Grid.Column(goldView).column(Size.ExtraSmall, valueColumnSize)
    ),
    Grid.Row(
      Grid.Column("Gotowość:").column(Size.ExtraSmall, labelColumnSize),
      Grid.Column(preparationView).column(Size.ExtraSmall, valueColumnSize)
    ),
    Grid.Row(
      Grid.Column("Poszukiwacz:").column(Size.ExtraSmall, labelColumnSize),
      Grid.Column(heroView).column(Size.ExtraSmall, valueColumnSize)
    ),
    Grid.Row(
      Grid.Column("Budynki:").column(Size.ExtraSmall, labelColumnSize),
      Grid.Column(buildingsView).column(Size.ExtraSmall, valueColumnSize)
    )
  ))
}
