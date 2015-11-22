package io.github.marad.cnd.core

import io.github.marad.cnd.dungeon.actions._
import io.github.marad.cnd.dungeon.buildings._
import io.github.marad.cnd.widgets.Log

import scala.util.Random

trait DungeonActions {
  val random: Random
  val log: Log

  val dungeonActions = Seq(
    BuildCrystal, Build(Gate), Build(new GoblinsDen(random, log)), Build(Trap),
    Build(new UndegroundGraveyard(random, log)), BuildIllusionNet)

}
