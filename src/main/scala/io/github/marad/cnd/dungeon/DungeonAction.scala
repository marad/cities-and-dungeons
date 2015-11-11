package io.github.marad.cnd.dungeon

import io.github.marad.cnd.Action

trait DungeonAction extends Action[Dungeon] {
  def cost: Int
  def duration: Float
  def name: String

  def doAction(dungeon: Dungeon): Dungeon

  override def apply(dungeon: Dungeon): Dungeon =
    if (dungeon.energy >= cost) {
      doAction(dungeon.copy(energy = dungeon.energy - cost))
    } else {
      dungeon
    }
}
