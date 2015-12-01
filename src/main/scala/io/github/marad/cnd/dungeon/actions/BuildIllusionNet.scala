package io.github.marad.cnd.dungeon.actions

import io.github.marad.cnd.dungeon.buildings.IllusionNet
import io.github.marad.cnd.dungeon.{Dungeon, DungeonAction}
import org.widok.{View, Var}

object BuildIllusionNet extends DungeonAction {
  val cost: Var[Int] = IllusionNet.cost
  val name: String = IllusionNet.name
  val duration: Var[Float] = IllusionNet.buildTime

  override def turnStartEffect(dungeon: Dungeon): Dungeon = {
    dungeon.build(new IllusionNet())
  }

  override val additionalSettings: View = IllusionNet.additionalSettings
}
