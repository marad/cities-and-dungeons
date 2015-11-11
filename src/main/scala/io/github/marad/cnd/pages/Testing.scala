package io.github.marad.cnd.pages

import io.github.marad.cnd.Game
import io.github.marad.cnd.city.actions._
import io.github.marad.cnd.dungeon.actions.{BuildCrystal, Build}
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

  val dayTime = Var(1f)
  val nightTime = Var(1f)
  val cityActions = Seq(BrewPotions, BuildArmorer, BuildMagesGuild, LoversKiss, Raid, Taxes, WarMeeting)
  val dungeonActions = Seq(BuildCrystal, Build(Gate), Build(GoblinsDen), Build(Trap), Build(UndegroundGraveyard), Build(new IllusionNet))

  val cityActionsView = div(
    div("Czas: ", dayTime),
    cityActions.map(action => {
      Button(action.name, " | ", action.cost, "zł | Czas: ", action.duration)
        .onClick(_ => {
          Game.city := action(Game.city.get)
          dayTime.update(_ - action.duration)
        })
        .enabled(Game.city.zip(dayTime).map(v => action.cost <= v._1.gold && action.duration <= v._2))
        .width(Length.Percentage(1))
        .css("action-btn")
    }),
    Button("Koniec dnia")
      .onClick(_ => {
        Game.city := Game.city.get.endTurn()
        Game.city := Game.city.get.beginTurn()
        dayTime := 1f
        timeOfDay := Night
      })
  )

  val dungeonActionsView = div(
    div("Czas: ", nightTime),
    dungeonActions.map(action => {
      Button(action.name, " | ", action.cost, "e | Czas: ", action.duration)
        .onClick(_ => {
          Game.dungeon := action(Game.dungeon.get)
          nightTime.update(_ - action.duration)
        })
        .enabled(Game.dungeon.zip(nightTime).map(v => action.cost <= v._1.energy && action.duration <= v._2))
        .width(Length.Percentage(1))
        .css("action-btn")
    }),
    Button("Koniec nocy")
      .onClick(_ => {
        Game.dungeon := Game.dungeon.get.endTurn()
        Game.dungeon := Game.dungeon.get.beginTurn()
        nightTime := 1f
        timeOfDay := Day
      })
  )

//  override def view(): View = div(
//    Grid.Row(
//      Grid.Column("Dzień: ", dayTime).column(Size.ExtraSmall, 1),
//      Grid.Column(CityView(Game.city)).column(Size.ExtraSmall, 7),
//      Grid.Column(cityActionsView).column(Size.ExtraSmall, 4)
//    ),
//    Grid.Row(
//      Grid.Column("Noc: ", nightTime).column(Size.ExtraSmall, 1),
//      Grid.Column(DungeonView(Game.dungeon)).column(Size.ExtraSmall, 7),
//      Grid.Column(dungeonActionsView).column(Size.ExtraSmall, 4)
//    )
//  )

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
