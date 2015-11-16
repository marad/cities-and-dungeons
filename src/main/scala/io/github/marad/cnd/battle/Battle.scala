package io.github.marad.cnd.battle

import io.github.marad.cnd.city.{Potion, Kiss, Hero}
import io.github.marad.cnd.dungeon.Dungeon
import io.github.marad.cnd.dungeon.buildings.Gate

import scala.annotation.tailrec

case class BattleTurn()
case class Report(turns: Seq[BattleTurn])

sealed trait BattleEvent {
  def desc: String
}

case object KissDefends extends BattleEvent {
  val desc = "Pocałunek"
}

case object GateDestroyed extends BattleEvent {
  val desc = "Poszukiwacz traci poziom niszcząc wrota"
}

case object Encounter extends BattleEvent {
  val desc = "Starcie!"
}

case object UsePotion extends BattleEvent {
  val desc = "Mikstura przywraca pierwotny poziom poszukiwacza"
}

class Battle(hero: Hero, dungeon: Dungeon) {
  type Events = Seq[BattleEvent]
  case class Info(hero: Hero, dungeon: Dungeon, events: Events)

  def battle(): Info = {
    playTurns(checkGates(hero, dungeon))
  }

  private def checkGates(h: Hero, d: Dungeon): Info =
    if (d.buildings.contains(Gate)) {
      Info(h.copy(level = h.level-1), d.destroy(Gate), Seq(GateDestroyed))
    } else Info(h, d, Seq())

  @tailrec
  private def playTurns(info: Info): Info =
    if (info.hero.level <= 0 && !info.hero.buffs.contains(Potion)) {
      info
    } else {
      playTurns(singleTurn(info))
    }

  private def singleTurn(info: Info): Info = info match {
    case Info(h, d, events) if h.buffs.contains(Kiss) =>
      Info(h.copy(buffs = h.buffs - Kiss), d, events :+ KissDefends)

    case Info(h, d, events) if h.level <= 1 && h.buffs.contains(Potion) =>
      Info(h.copy(level = hero.level, buffs = h.buffs - Potion), d, events :+ UsePotion)

    case Info(h, d, events) =>
      Info(h.copy(level = h.level - d.difficulty), d, events :+ Encounter)
  }
}

