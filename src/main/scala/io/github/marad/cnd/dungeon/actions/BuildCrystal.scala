package io.github.marad.cnd.dungeon.actions

import io.github.marad.cnd.dungeon.{Dungeon, DungeonAction}

object BuildCrystal extends DungeonAction {
  val cost: Int = 5
  val duration: Float = 0.5f
  val name: String = "Wybuduj Kryształ Energii"

  override def doAction(dungeon: Dungeon): Dungeon =
    dungeon.copy(crystals = dungeon.crystals + 1)
}
