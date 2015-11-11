package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Game
import io.github.marad.cnd.dungeon.Dungeon
import org.widok.Var

object UndegroundGraveyard extends MonsterHive {
  val name: String = "Podziemne Cmentarzysko"
  val cost = Var(10)
  val buildTime = Var(1f)

  override def onBuilt(dungeon: Dungeon): Dungeon =
    dungeon.copy(strength = dungeon.strength + 2)

  override def onDestroyed(dungeon: Dungeon): Dungeon =
    dungeon.copy(strength = dungeon.strength - 2)

  override def onTurnEnd(dungeon: Dungeon): Dungeon = {
    if (Game.random.nextInt(1000) <= 100) {
      val hives = dungeon.buildings.filter {
        case m: MonsterHive => m != UndegroundGraveyard
        case _ => false
      }
      val hiveToRemove = hives(Game.random.nextInt(hives.size))
      dungeon.destroy(hiveToRemove)
    } else {
      dungeon
    }
  }
}
