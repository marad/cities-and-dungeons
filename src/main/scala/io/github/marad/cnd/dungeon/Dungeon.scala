package io.github.marad.cnd.dungeon

import io.github.marad.cnd.Building

case class Dungeon(
                  energy: Int = 20,
                  strength: Int = 0,
                  crystals: Int = 2,
                  buildings: Seq[Building[Dungeon]] = Seq()
                  ) {
  def beginTurn(): Dungeon =
    buildings.foldLeft[Dungeon => Dungeon](identity)(
      (f, b) => f andThen b.onTurnStart
    )(copy(energy = energy + crystals * 2))

  def endTurn(): Dungeon =
    buildings.foldLeft[Dungeon => Dungeon](identity)(
      (f, b) => f andThen b.onTurnEnd
    )(this)

  def build(building: Building[Dungeon]): Dungeon =
    building.onBuilt(copy(buildings = buildings :+ building))

  def destroy(building: Building[Dungeon]): Dungeon =
    building.onDestroyed(copy(buildings = buildings diff List(building)))
}
