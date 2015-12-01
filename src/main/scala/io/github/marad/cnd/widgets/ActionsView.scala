package io.github.marad.cnd.widgets

import io.github.marad.cnd.{ActionSubject, Action}
import org.scalajs.dom.html.Element
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class ActionsView[A <: Action[T], T <: ActionSubject[A]](actions: Seq[A], subject: Var[T]) extends Widget[ActionsView[A, T]] {
  val remainingTime = Var(1f)
  val endTurn = Opt[Boolean]()
  val selectedActions = Channel[A]()

  override val rendered: Element = DOM.createElement("span", Seq(
    div("Czas: ", remainingTime),
    actions.map(action => {
      Button(action.name, " | Koszt: ", action.cost, " | Czas: ", action.duration)
        .onClick(_ => {
          selectedActions := action
          remainingTime.update(_ - action.duration.get)
        })
        .enabled(subject.zip(remainingTime).map(v => v._1.canPerform(action) && action.duration.get <= v._2))
        .width(Length.Percentage(1))
        .css("action-btn")
    }),
    Button("Koniec").onClick(_ => {
      remainingTime := 1f
      endTurn := true
    })
  ))
}
