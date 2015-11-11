package io.github.marad.cnd.dungeon.actions

import io.github.marad.cnd.Building
import io.github.marad.cnd.dungeon.{DungeonAction, Dungeon}

case class Build(building: Building[Dungeon]) extends DungeonAction {
  override def cost: Int = building.cost
  override def name: String = "Wybuduj " + building.name
  override def duration: Float = building.buildTime

  override def doAction(dungeon: Dungeon): Dungeon =
    dungeon.build(building)
}
