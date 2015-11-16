package io.github.marad.cnd.dayCycle

sealed trait TimeOfDay

case object DayStart extends TimeOfDay
case object DayEnd extends TimeOfDay
case object NightStart extends TimeOfDay
case object NightEnd extends TimeOfDay

