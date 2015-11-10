package io.github.marad.cnd.pages

import org.widok._
import org.widok.html._

case class Testing() extends Page {
  override def view(): View = div("Hello World")

  override def ready(route: InstantiatedRoute): Unit = {}
}
