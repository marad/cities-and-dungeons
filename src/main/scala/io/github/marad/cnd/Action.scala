package io.github.marad.cnd

trait Action[T] extends (T => T)
