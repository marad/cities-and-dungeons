package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Game
import io.github.marad.cnd.dungeon.Dungeon
import io.github.marad.cnd.dungeon.actions.Build

object GoblinsDen extends MonsterHive {
  override val name: String = "Siedlisko Goblinów"
  override val cost: Int = 10
  override val buildTime: Float = 0.5f

  override def onBuilt(t: Dungeon): Dungeon =
    t.copy(strength = t.strength + 1)

  override def onDestroyed(t: Dungeon): Dungeon =
    t.copy(strength = t.strength - 1)

  override def onTurnStart(t: Dungeon): Dungeon =
    if (Game.random.nextInt(1000) <= 100 && t.energy >= cost) {
      (new Build(this))(t)
    } else t

}
