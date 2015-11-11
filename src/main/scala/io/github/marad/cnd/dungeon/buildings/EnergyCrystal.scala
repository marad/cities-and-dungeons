package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.dungeon.{Dungeon, Building}

object EnergyCrystal extends Building {
  override val name: String = "Kryształ Energii"

  override def apply(dungeon: Dungeon): Dungeon =
    dungeon.copy(energy = dungeon.energy + 1)
}
