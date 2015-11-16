package io.github.marad.cnd.pages

import io.github.marad.cnd.{Game, Action}
import io.github.marad.cnd.city.actions._
import io.github.marad.cnd.dungeon.actions.{Build, BuildIllusionNet, BuildCrystal}
import io.github.marad.cnd.dungeon.buildings.{UndegroundGraveyard, Trap, GoblinsDen, Gate}
import io.github.marad.cnd.widgets.Toolbar
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class Settings(game: Game)() extends Page {
  val cityActions = Seq(
    BrewPotions, BuildArmorer, BuildMagesGuild,
    LoversKiss, Raid, Taxes, WarMeeting
  )

  val dungeonActions = Seq(
    BuildCrystal, Build(Gate), Build(new GoblinsDen(game)), Build(Trap),
    Build(new UndegroundGraveyard(game)), BuildIllusionNet)

  override def view(): View = div(
    Toolbar(game),
    h1("Miasto"),
    div(cityActions.map(actionView)),
    h1("Loch"),
    div(dungeonActions.map(actionView))
  )

  override def ready(route: InstantiatedRoute): Unit = {}

  private def actionView[T](action: Action[T]) =
    Grid.Row(
      Grid.Column(b(action.name)).column(Size.ExtraSmall, 4),
      Grid.Column("Koszt:").column(Size.ExtraSmall, 1),
      Grid.Column(Input.Number().bind(action.cost.biMap(_.toString, _.toInt))).column(Size.ExtraSmall, 3),
      Grid.Column("Czas:").column(Size.ExtraSmall, 1),
      Grid.Column(Input.Number().bind(action.duration.biMap(_.toString, _.toFloat))).column(Size.ExtraSmall, 3)
    )
}
