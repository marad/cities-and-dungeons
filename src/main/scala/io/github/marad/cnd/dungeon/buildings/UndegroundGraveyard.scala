package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Game
import io.github.marad.cnd.dungeon.{Dungeon, Building}

object UndegroundGraveyard extends Building {
  override val name: String = "Podziemne Cmentarzysko"

  override def apply(dungeon: Dungeon): Dungeon =
    if (Game.random.nextInt(1000) <= 100) {
      val buildingToRemove = dungeon.buildings.filter(_ != EnergyCrystal)()
    } else {
      dungeon
    }

}
