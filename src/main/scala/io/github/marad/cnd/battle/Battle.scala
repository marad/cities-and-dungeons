package io.github.marad.cnd.battle

import io.github.marad.cnd.city.{Potion, Kiss, Hero}
import io.github.marad.cnd.dungeon.Dungeon
import io.github.marad.cnd.dungeon.buildings.Gate

import scala.annotation.tailrec

case class BattleTurn()
case class Report(turns: Seq[BattleTurn])

sealed trait BattleEvent
case object KissDefends extends BattleEvent
case object GateDestroyed extends BattleEvent
case object Encounter extends BattleEvent
case object UsePotion extends BattleEvent


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

  private def singleTurn(info: Info): Info = {
    case i@Info(h, d, events) if h.buffs.contains(Kiss) =>
      Info(h.copy(buffs = h.buffs - Kiss), d, events :+ KissDefends)

    case Info(h, d, events) if h.level == 0 && h.buffs.contains(Potion) =>
      Info(h.copy(level = hero.level), d, events :+ UsePotion)

    case Info(h, d, events) =>
      Info(h.copy(level = h.level - d.difficulty), d, events :+ Encounter)
  }

//  private def singleTurn(info: Info): Info = {
//    if(info.hero.buffs.contains(Kiss)) {
//      info.copy(hero = info.hero.copy(buffs = info.hero.buffs - Kiss), events = info.events :+ KissDefends)
//    }
//    else if (info.hero.level <= 0 && info.hero.buffs)
//  }
}

