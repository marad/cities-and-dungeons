package io.github.marad.cnd.widgets

import io.github.marad.cnd.city.Building
import org.scalajs.dom.html.Element
import org.widok._

case class BuildingsView(buildings: ReadChannel[Seq[Building]]) extends Widget[BuildingsView] {
  override val rendered: Element = DOM.createElement("span", Seq(
    buildings.map(_.map(_.name).mkString(", "))
  ))
}
