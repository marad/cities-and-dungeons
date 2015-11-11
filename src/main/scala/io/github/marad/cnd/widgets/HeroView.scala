package io.github.marad.cnd.widgets

import io.github.marad.cnd.city.Hero
import org.scalajs.dom.html.Element
import org.widok._

case class HeroView(hero: ReadChannel[Hero]) extends Widget[HeroView] {
  override val rendered: Element = DOM.createElement("span", Seq(
    "Poziom: ", hero.map(_.level),
    " (",
    hero.map(_.buffs.map(_.name).mkString(", ")),
    ")"
  ))
}
