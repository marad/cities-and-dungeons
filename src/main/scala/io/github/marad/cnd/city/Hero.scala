package io.github.marad.cnd.city

sealed trait Buff {
  val name: String
}
case object Kiss extends Buff {
  val name = "Pocałunek"
}
case object Potion extends Buff {
  val name = "Mikstura"
}

case class Hero(level: Int = 0, buffs: Set[Buff] = Set())
