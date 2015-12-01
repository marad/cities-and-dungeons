package io.github.marad.cnd.dungeon

import io.github.marad.cnd.Action

trait DungeonAction extends Action[Dungeon] {
  protected def payForIt(dungeon: Dungeon): Dungeon =
    if (dungeon.energy >= cost.get)
      dungeon.copy(energy = dungeon.energy - cost.get)
    else dungeon

  override def instantEffect(city: Dungeon): Dungeon = payForIt(city)
  override def turnStartEffect(city: Dungeon): Dungeon
}
