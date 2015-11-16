package io.github.marad.cnd.widgets

import org.widok._
import org.widok.html._

class Log {
  private val messages = Buffer[View]()
  def info(view: View) = messages.prepend(view)
  def view: View = messages.map(div(_))
}
