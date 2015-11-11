package io.github.marad.cnd.dungeon

import io.github.marad.cnd.Action
import org.widok._

trait DungeonAction extends Action[Dungeon] {
  def cost: Var[Int]
  def duration: Var[Float]
  def name: String

  def doAction(dungeon: Dungeon): Dungeon

  override def apply(dungeon: Dungeon): Dungeon =
    if (dungeon.energy >= cost.get) {
      doAction(dungeon.copy(energy = dungeon.energy - cost.get))
    } else {
      dungeon
    }
}
