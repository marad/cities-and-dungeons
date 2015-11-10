package io.github.marad.cnd.city

sealed trait Buff
case object Kiss
case object Mixture

case class Hero(level: Int, buffs: Set[Buff])
