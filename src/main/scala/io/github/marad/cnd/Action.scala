package io.github.marad.cnd

import org.widok.Var

trait Action[T] extends (T => T) {
  def cost: Var[Int]
  def duration: Var[Float]
  def name: String
}
