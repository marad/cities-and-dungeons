package io.github.marad.cnd.widgets

import io.github.marad.cnd.Building
import org.scalajs.dom.html.Element
import org.widok._

case class BuildingsView[T <: {}](buildings: ReadChannel[Seq[Building[T]]]) extends Widget[BuildingsView[T]] {
  override val rendered: Element = DOM.createElement("span", Seq(
    buildings.map(_.map(_.name).mkString(", "))
  ))
}
