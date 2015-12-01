package io.github.marad.cnd.dungeon

import io.github.marad.cnd.{ActionSubject, Building}
import io.github.marad.cnd.dungeon.buildings.IllusionNet

case class Dungeon(
                  energy: Int = 20,
                  strength: Int = 0,
                  crystals: Int = 2,
                  buildings: Seq[Building[Dungeon]] = Seq()
                  ) extends ActionSubject[DungeonAction] {
  def canPerform(action: DungeonAction) = action.cost.get <= energy

  def beginTurn(): Dungeon =
    buildings.foldLeft[Dungeon => Dungeon](identity)(
      (f, b) => f andThen b.onTurnStart
    )(copy(energy = energy + crystals * 2))

  def endTurn(): Dungeon =
    buildings.foldLeft[Dungeon => Dungeon](identity)(
      (f, b) => {
        println(b)
        f andThen b.onTurnEnd
      }
    )(this)

  def build(building: Building[Dungeon]): Dungeon =
    building.onBuilt(copy(buildings = buildings :+ building))

  def destroy(building: Building[Dungeon]): Dungeon =
    building.onDestroyed(copy(buildings = buildings diff List(building)))

  def difficulty: Int = {
    val dif = 0.25 * (if (buildings.exists(_.isInstanceOf[IllusionNet])) strength * 2 else strength)
    if (dif <= 1) 1 else Math.round(dif).toInt
  }
}
