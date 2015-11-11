package io.github.marad.cnd.pages

import io.github.marad.cnd.Game
import io.github.marad.cnd.city.actions._
import io.github.marad.cnd.dungeon.actions.{BuildIllusionNet, BuildCrystal, Build}
import io.github.marad.cnd.dungeon.buildings._
import io.github.marad.cnd.widgets.{DungeonView, CityView}
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class Testing() extends Page {
  trait Time
  case object Day extends Time
  case object Night extends Time

  val timeOfDay = Var[Time](Day)
  val actionTime = Var(1f)

  timeOfDay.attach(_ => actionTime := 1f)

  val cityActions = Seq(BrewPotions, BuildArmorer, BuildMagesGuild, LoversKiss, Raid, Taxes, WarMeeting)
  val dungeonActions = Seq(BuildCrystal, Build(Gate), Build(GoblinsDen), Build(Trap), Build(UndegroundGraveyard), BuildIllusionNet)

  val cityActionsView = div(
    div("Czas: ", actionTime),
    cityActions.map(action => {
      Button(action.name, " | ", action.cost, "zł | Czas: ", action.duration)
        .onClick(_ => {
          Game.city := action(Game.city.get)
          actionTime.update(_ - action.duration.get)
        })
        .enabled(Game.city.zip(actionTime).map(v => action.cost.get <= v._1.gold && action.duration.get <= v._2))
        .width(Length.Percentage(1))
        .css("action-btn")
    }),
    Button("Koniec dnia")
      .onClick(_ => {
        Game.city := Game.city.get.endTurn()
        Game.city := Game.city.get.beginTurn()
        timeOfDay := Night
      })
  )

  val dungeonActionsView = div(
    div("Czas: ", actionTime),
    dungeonActions.map(action => {
      Button(action.name, " | ", action.cost, "e | Czas: ", action.duration)
        .onClick(_ => {
          Game.dungeon := action(Game.dungeon.get)
          actionTime.update(_ - action.duration.get)
        })
        .enabled(Game.dungeon.zip(actionTime).map(v => action.cost.get <= v._1.energy && action.duration.get <= v._2))
        .width(Length.Percentage(1))
        .css("action-btn")
    }),
    Button("Koniec nocy")
      .onClick(_ => {
        Game.dungeon := Game.dungeon.get.endTurn()
        Game.dungeon := Game.dungeon.get.beginTurn()
        timeOfDay := Day
      })
  )

  val actionsView = div(
    cityActionsView.show(timeOfDay.map(_ == Day)),
    dungeonActionsView.show(timeOfDay.map(_ == Night))
  )

  override def view(): View = div(
    Grid.Row(
      Grid.Column(
        div(CityView(Game.city)),
        div(DungeonView(Game.dungeon))
      ).column(Size.ExtraSmall, 6),
      Grid.Column(
        div(actionsView)
      ).column(Size.ExtraSmall, 6)
    )
  )

  override def ready(route: InstantiatedRoute): Unit = {}
}
