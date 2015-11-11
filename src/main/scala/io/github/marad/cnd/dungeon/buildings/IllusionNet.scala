package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Building
import io.github.marad.cnd.dungeon.Dungeon

class IllusionNet extends Building[Dungeon] {
  override val name: String = "Siatka Iluzji"
  override val cost: Int = 20
  override val buildTime: Float = 0

  private var turnsLeft = 2

  override def onTurnEnd(t: Dungeon): Dungeon =
    if (turnsLeft > 0) {
      turnsLeft -= 1
      t
    } else {
      t.destroy(this)
    }
}
