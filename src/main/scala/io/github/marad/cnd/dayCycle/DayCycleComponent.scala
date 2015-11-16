package io.github.marad.cnd.dayCycle

import scala.collection.mutable

trait DayCycleComponent {
  val dayCycle: DayCycle = new DayCycle()

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
      doActions(DayStart)
      doActions(NightStart)
    }
  }
}
