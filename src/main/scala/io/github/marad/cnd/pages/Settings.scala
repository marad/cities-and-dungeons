package io.github.marad.cnd.pages

import io.github.marad.cnd.{Game, Action}
import io.github.marad.cnd.widgets.Toolbar
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class Settings(game: Game)() extends Page {
  override def view(): View = div(
    Toolbar(game),
    h1("Miasto"),
    div(game.cityActions.map(actionView)),
    h1("Loch"),
    div(game.dungeonActions.map(actionView))
  )

  override def ready(route: InstantiatedRoute): Unit = {}

  private def actionView[T](action: Action[T]) =
    div(
      Grid.Row(
        Grid.Column(b(action.name)).column(Size.ExtraSmall, 4),
        Grid.Column("Koszt:").column(Size.ExtraSmall, 2),
        Grid.Column(Input.Number().bind(action.cost.biMap(_.toString, _.toInt))).column(Size.ExtraSmall, 2),
        Grid.Column("Czas:").column(Size.ExtraSmall, 2),
        Grid.Column(Input.Number().bind(action.duration.biMap(_.toString, _.toFloat))).column(Size.ExtraSmall, 2)
      ), action.additionalSettings
    )
}
