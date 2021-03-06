package io.github.marad.cnd.pages

import io.github.marad.cnd.city.City
import io.github.marad.cnd.dungeon.Dungeon
import io.github.marad.cnd.{Action, Game}
import io.github.marad.cnd.core.dayCycle.{TimeOfDay, NightStart, DayStart}
import io.github.marad.cnd.widgets._
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class GamePage(game: Game)() extends Page {
  val cityActionsView = ActionsView(game.cityActions, game.city)
  val dungeonActionsView = ActionsView(game.dungeonActions, game.dungeon)

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

  cityActionsView.selectedActions.attach(action => game.log.info("Akcja miasta: " + action.name))
  dungeonActionsView.selectedActions.attach(action => game.log.info("Akcja lochów: " + action.name))

  cityActionsView.endTurn.attach { _ => game.timeOfDay := NightStart }
  dungeonActionsView.endTurn.attach { _ => game.timeOfDay := DayStart }

  val actionsView = div(
    cityActionsView.show(game.timeOfDay.map(_ == DayStart)),
    dungeonActionsView.show(game.timeOfDay.map(_ == NightStart))
  )

  override def view(): View = div(
    Toolbar(game, () => {
      cityActionsView.remainingTime := 1
      dungeonActionsView.remainingTime := 1
      cityScheduledActions.clear()
      dungeonScheduledActions.clear()
    }),
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
    div(h2("Wydarzenia")),
    Grid.Row(
      Grid.Column(game.log.view)
    )
  )

  override def ready(route: InstantiatedRoute): Unit = {}
}
