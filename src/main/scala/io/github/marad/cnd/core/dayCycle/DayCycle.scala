package io.github.marad.cnd.core.dayCycle

sealed trait TimeOfDay
sealed trait TurnChanger

case object DayStart extends TimeOfDay with TurnChanger
case object DayEnd extends TimeOfDay
case object NightStart extends TimeOfDay with TurnChanger
case object NightEnd extends TimeOfDay

