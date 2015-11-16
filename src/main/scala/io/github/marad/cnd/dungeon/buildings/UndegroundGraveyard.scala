package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Game
import io.github.marad.cnd.dungeon.Dungeon
import org.widok.Var

class UndegroundGraveyard(game: Game) extends MonsterHive {
  val name: String = "Podziemne Cmentarzysko"
  val cost = Var(10)
  val buildTime = Var(1f)

  override def onBuilt(dungeon: Dungeon): Dungeon =
    dungeon.copy(strength = dungeon.strength + 2)

  override def onDestroyed(dungeon: Dungeon): Dungeon =
    dungeon.copy(strength = dungeon.strength - 2)

  override def onTurnEnd(dungeon: Dungeon): Dungeon = {
    if (game.random.nextInt(1000) <= 100) {
      val hives = dungeon.buildings.filter {
        case m: UndegroundGraveyard => false
        case m: MonsterHive => true
        case _ => false
      }
      val hiveToRemove = hives(game.random.nextInt(hives.size))
      dungeon.destroy(hiveToRemove)
    } else {
      dungeon
    }
  }
}
