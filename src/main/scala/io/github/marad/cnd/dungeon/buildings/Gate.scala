package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Building
import io.github.marad.cnd.dungeon.Dungeon

object Gate extends Building[Dungeon] {
  override val name: String = "Wrota"
  override val cost: Int = 2
  override val buildTime: Float = 0.5f
}
