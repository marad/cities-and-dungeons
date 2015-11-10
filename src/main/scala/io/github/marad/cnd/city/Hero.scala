package io.github.marad.cnd.city

sealed trait Buff
case object Kiss extends Buff
case object Potion extends Buff

case class Hero(level: Int = 0, buffs: Set[Buff] = Set())
