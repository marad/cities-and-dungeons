package io.github.marad.cnd.pages

import io.github.marad.cnd.battle.Battle
import io.github.marad.cnd.city._
import io.github.marad.cnd.dungeon._
import io.github.marad.cnd.dungeon.buildings.Gate
import io.github.marad.cnd.widgets.{DungeonView, HeroView}
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class BattleTest() extends Page {
  val hero = Hero(5, Set(Kiss, Potion))
  val dungeon = Dungeon(strength = 5, buildings = Seq(Gate))

  val battle = new Battle(hero, dungeon)
  val result = battle.battle()

  override def view(): View = div(
    result.events.map(event => div(event.desc)),
    HeroView(Var(result.hero)),
    DungeonView(Var(result.dungeon))
  )

  override def ready(route: InstantiatedRoute): Unit = {}
}
