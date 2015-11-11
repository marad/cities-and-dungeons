package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Building
import io.github.marad.cnd.dungeon.Dungeon
import org.widok.Var

object Trap extends Building[Dungeon] {
  override val name: String = "Pułapka"
  override val cost = Var(20)
  override val buildTime = Var(1f)
}
