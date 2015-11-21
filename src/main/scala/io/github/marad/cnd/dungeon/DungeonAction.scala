package io.github.marad.cnd.dungeon

import io.github.marad.cnd.Action

trait DungeonAction extends Action[Dungeon] {
  def doAction(dungeon: Dungeon): Dungeon

  override def instantEffect(dungeon: Dungeon): Dungeon =
    if (dungeon.energy >= cost.get) {
      doAction(dungeon.copy(energy = dungeon.energy - cost.get))
    } else {
      dungeon
    }
}
