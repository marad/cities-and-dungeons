package io.github.marad.cnd

trait Building[T] {
  val name: String
  val cost: Int
  val buildTime: Float

  def onBuilt(t: T): T = t

  def onDestroyed(t: T): T = t

  def onTurnStart(t: T): T = t

  def onTurnEnd(t: T): T = t
}
