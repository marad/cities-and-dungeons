package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Building
import io.github.marad.cnd.dungeon.Dungeon

object Trap extends Building[Dungeon] {
  override val name: String = "Pułapka"
  override val cost: Int = 20
  override val buildTime: Float = 1f
}
