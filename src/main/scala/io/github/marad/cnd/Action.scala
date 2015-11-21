package io.github.marad.cnd

import org.widok.Var

trait Action[T] {
  def cost: Var[Int]
  def duration: Var[Float]
  def name: String

  def instantEffect(t: T): T = t
  def turnStartEffect(t: T): T = t
}
