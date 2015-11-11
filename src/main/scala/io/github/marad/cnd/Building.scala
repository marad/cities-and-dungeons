package io.github.marad.cnd

import org.widok._

trait Building[T] {
  val name: String
  val cost: Var[Int]
  val buildTime: Var[Float]

  def onBuilt(t: T): T = t

  def onDestroyed(t: T): T = t

  def onTurnStart(t: T): T = t

  def onTurnEnd(t: T): T = t
}
