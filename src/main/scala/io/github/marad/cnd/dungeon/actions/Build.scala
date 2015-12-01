package io.github.marad.cnd.dungeon.actions

import io.github.marad.cnd.Building
import io.github.marad.cnd.dungeon.{DungeonAction, Dungeon}
import org.widok.Var

case class Build(building: Building[Dungeon]) extends DungeonAction {
  override def name: String = "Wybuduj " + building.name
  override def cost: Var[Int] = building.cost
  override def duration: Var[Float] = building.buildTime

  override def turnStartEffect(dungeon: Dungeon): Dungeon =
    dungeon.build(building)
}
