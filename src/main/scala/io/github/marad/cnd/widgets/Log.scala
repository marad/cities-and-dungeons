package io.github.marad.cnd.widgets

import org.widok._
import org.widok.html._

sealed trait LogEntry {
  def view: View
}

case class CustomEntry(view: View) extends LogEntry

case class MessageEntry(message: String) extends LogEntry {
  val view = div(message)
}

class Log {
  private val entries = Buffer[LogEntry]()
  def info(message: String) = entries.prepend(new MessageEntry(message))
  def custom(view: View) = entries.prepend(new CustomEntry(view))
  def view: View = entries.map(e => div(e.view))
  def clear() = entries.clear()
}
