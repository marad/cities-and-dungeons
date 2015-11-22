package io.github.marad.cnd.pages

import io.github.marad.cnd.city.City
import io.github.marad.cnd.dungeon.Dungeon
import io.github.marad.cnd.{Action, Game}
import io.github.marad.cnd.city.actions._
import io.github.marad.cnd.dayCycle.{TimeOfDay, NightStart, DayStart}
import io.github.marad.cnd.dungeon.actions.{BuildIllusionNet, BuildCrystal, Build}
import io.github.marad.cnd.dungeon.buildings._
import io.github.marad.cnd.widgets._
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class GamePage(game: Game)() extends Page {

  val cityActions = Seq(
    BrewPotions, BuildArmorer, BuildMagesGuild,
    LoversKiss, Raid, Taxes, WarMeeting)

  val dungeonActions = Seq(
    BuildCrystal, Build(Gate), Build(new GoblinsDen(game)), Build(Trap),
    Build(new UndegroundGraveyard(game)), BuildIllusionNet)

  val cityActionsView = ActionsView(cityActions, game.city)
  val dungeonActionsView = ActionsView(dungeonActions, game.dungeon)

  val cityScheduledActions = Buffer[Action[City]]()
  val dungeonScheduledActions = Buffer[Action[Dungeon]]()

  def mutateChannel[T](subject: StateChannel[T], mutator: T => T) = subject := mutator(subject.get)
  def handleSelectedAction[T](timeOfDay: TimeOfDay, subject: StateChannel[T], scheduledActions: Buffer[Action[T]])
                             (action: Action[T]) = {
    scheduledActions += action
    mutateChannel(subject, action.instantEffect)
    game.dayCycle.addAction(timeOfDay, () => {
      scheduledActions.clear()
      mutateChannel(subject, action.turnStartEffect)
    })
  }

  cityActionsView.selectedActions.attach(handleSelectedAction(DayStart, game.city, cityScheduledActions))
  dungeonActionsView.selectedActions.attach(handleSelectedAction(NightStart, game.dungeon, dungeonScheduledActions))

  Seq(cityActionsView, dungeonActionsView).foreach(av => av.selectedActions.attach(action => {
    game.log.info("Akcja: " + action.name)
  }))

  sealed trait Time
  case object Day extends Time
  case object Night extends Time

  val timeOfDay = Var[Time](Day)

  cityActionsView.endTurn.attach { _ => timeOfDay := Night }
  dungeonActionsView.endTurn.attach { _ => timeOfDay := Day }

  timeOfDay.filter(_ == Day).attach(_ => {
    game.dayCycle.startDay()
    game.dungeon := game.dungeon.get.endTurn()
    game.city := game.city.get.beginTurn()
    game.log.info("Wshodzi słońce")
  })

  timeOfDay.filter(_ == Night).attach(_ => {
    game.dayCycle.startNight()
    game.city := game.city.get.endTurn()
    game.dungeon := game.dungeon.get.beginTurn()
    game.log.info("Nastaje noc")
  })

  val actionsView = div(
    cityActionsView.show(timeOfDay.map(_ == Day)),
    dungeonActionsView.show(timeOfDay.map(_ == Night))
  )

  override def view(): View = div(
    Toolbar(game),
    Grid.Row(
      Grid.Column(
        div(CityView(game.city)),
        div("Akcje: ", ul(cityScheduledActions.map(_.name).map(li(_))).css("comma-list")),
        div(DungeonView(game.dungeon)),
        div("Akcje: ", ul(dungeonScheduledActions.map(_.name).map(li(_))).css("comma-list"))
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
