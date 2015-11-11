package io.github.marad.cnd.dungeon.actions

import io.github.marad.cnd.dungeon.{Dungeon, DungeonAction}
import org.widok.Var

object BuildCrystal extends DungeonAction {
  val cost: Var[Int] = Var(5)
  val duration: Var[Float] = Var(0.5f)
  val name: String = "Wybuduj Kryształ Energii"

  override def doAction(dungeon: Dungeon): Dungeon =
    dungeon.copy(crystals = dungeon.crystals + 1)
}
