package io.github.marad.cnd.pages

import io.github.marad.cnd.Game
import io.github.marad.cnd.city.actions._
import io.github.marad.cnd.dayCycle.{NightStart, DayStart}
import io.github.marad.cnd.dungeon.actions.{BuildIllusionNet, BuildCrystal, Build}
import io.github.marad.cnd.dungeon.buildings._
import io.github.marad.cnd.widgets._
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class GamePage(game: Game)() extends Page {
  trait Time
  case object Day extends Time
  case object Night extends Time

  val timeOfDay = Var[Time](Day)
  val actionTime = Var(1f)
  val cityScheduledActions = Buffer[String]()
  val dungeonScheduledActions = Buffer[String]()

  timeOfDay.attach(_ => actionTime := 1f)

  timeOfDay.filter(_ == Day).attach(_ => {
    game.dayCycle.startDay()
    cityScheduledActions.clear()
  })
  timeOfDay.filter(_ == Night).attach(_ => {
    game.dayCycle.startNight()
    dungeonScheduledActions.clear()
  })

  val cityActions = Seq(
    BrewPotions, BuildArmorer, BuildMagesGuild,
    LoversKiss, Raid, Taxes, WarMeeting)
  val dungeonActions = Seq(
    BuildCrystal, Build(Gate), Build(new GoblinsDen(game)), Build(Trap),
    Build(new UndegroundGraveyard(game)), BuildIllusionNet)

  val cityActionsView = div(
    div("Czas: ", actionTime),
    cityActions.map(action => {
      Button(action.name, " | ", action.cost, "zł | Czas: ", action.duration)
        .onClick(_ => {
          actionTime.update(_ - action.duration.get)
          cityScheduledActions += action.name
          game.city := action.instantEffect(game.city.get)
          game.dayCycle.addAction(DayStart, () => {
            game.city := action.turnStartEffect(game.city.get)
          })
        })
        .enabled(game.city.zip(actionTime).map(v => action.cost.get <= v._1.gold && action.duration.get <= v._2))
        .width(Length.Percentage(1))
        .css("action-btn")
    }),
    div(cityScheduledActions.foldLeft("")((acc, s) => acc + ", " + s)),
    div(cityScheduledActions.head.flatMap(h => cityScheduledActions.tail.foldLeft(h)((a, b) => s"$a, $b"))),
    Button("Koniec dnia")
      .onClick(_ => {
        game.city := game.city.get.endTurn()
        game.city := game.city.get.beginTurn()
        timeOfDay := Night
      })
  )

  val dungeonActionsView = div(
    div("Czas: ", actionTime),
    dungeonActions.map(action => {
      Button(action.name, " | ", action.cost, "e | Czas: ", action.duration)
        .onClick(_ => {
          actionTime.update(_ - action.duration.get)
          dungeonScheduledActions += action.name
          game.dungeon := action.instantEffect(game.dungeon.get)
          game.dayCycle.addAction(NightStart, () => {
            game.dungeon := action.turnStartEffect(game.dungeon.get)
          })
        })
        .enabled(game.dungeon.zip(actionTime).map(v => action.cost.get <= v._1.energy && action.duration.get <= v._2))
        .width(Length.Percentage(1))
        .css("action-btn")
    }),
    div(dungeonScheduledActions),
    Button("Koniec nocy")
      .onClick(_ => {
        game.dungeon := game.dungeon.get.endTurn()
        game.dungeon := game.dungeon.get.beginTurn()
        timeOfDay := Day
      })
  )


  val actionsView = div(
    cityActionsView.show(timeOfDay.map(_ == Day)),
    dungeonActionsView.show(timeOfDay.map(_ == Night))
  )


  override def view(): View = div(
    Toolbar(game),
    Grid.Row(
      Grid.Column(
        div(CityView(game.city)),
        div(DungeonView(game.dungeon))
      ).column(Size.ExtraSmall, 6),
      Grid.Column(
        div(actionsView)
      ).column(Size.ExtraSmall, 6)
    ),
    Grid.Row(
      Grid.Column(game.log.view)
    )
  )

  override def ready(route: InstantiatedRoute): Unit = {}
}
