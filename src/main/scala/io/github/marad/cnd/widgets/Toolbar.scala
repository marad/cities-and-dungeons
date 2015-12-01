package io.github.marad.cnd.widgets

import io.github.marad.cnd.{Game, Main}
import org.scalajs.dom.html.Element
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class Toolbar(game: Game, onReset: () => Unit = () => {}) extends Widget[Toolbar] {
  override val rendered: Element = DOM.createElement("span", Seq(
    div(
      Button("Gra").onClick(_ => Main.testing().go()),
      Button("Ustawienia").onClick(_ => Main.settings().go()),
      " | ",
      Button("Resetuj Grę").onClick(_ => {
        game.resetGame()
        onReset()
      })
    )
  ))
}
