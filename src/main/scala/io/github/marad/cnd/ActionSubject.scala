package io.github.marad.cnd

trait ActionSubject[ActionType] {
  def canPerform(action: ActionType): Boolean
}
