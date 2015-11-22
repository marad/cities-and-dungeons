package io.github.marad.cnd.core.dayCycle

import org.widok.Var

import scala.collection.mutable

trait DayCycleComponent {
  val timeOfDay = Var[TimeOfDay with TurnChanger](DayStart)
  val dayCycle: DayCycle = new DayCycle()

  val days = timeOfDay.filter(_ == DayStart)
  val nights = timeOfDay.filter(_ == NightStart)

  days.attach(_ => dayCycle.startDay())
  nights.attach(_ => dayCycle.startNight())

  class DayCycle {
    type Action = () => Any

    val actions = mutable.Map[TimeOfDay, List[Action]]()

    def addAction(timeOfDay: TimeOfDay, action: Action) =
      actions.update(timeOfDay, action +: actions.getOrElse(timeOfDay, List()))

    def doActions(timeOfDay: TimeOfDay) = {
      actions.get(timeOfDay).map(_.map(_()))
      actions.remove(timeOfDay)
    }

    def startDay() = {
      doActions(NightEnd)
      doActions(DayStart)
    }

    def startNight() = {
      doActions(DayEnd)
      doActions(NightStart)
    }
  }
}
