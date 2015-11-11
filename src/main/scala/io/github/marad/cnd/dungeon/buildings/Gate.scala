package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Building
import io.github.marad.cnd.dungeon.Dungeon
import org.widok.Var

object Gate extends Building[Dungeon] {
  val name: String = "Wrota"
  val cost = Var(2)
  val buildTime = Var(0.5f)
}
