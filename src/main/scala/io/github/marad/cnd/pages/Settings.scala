package io.github.marad.cnd.pages

import io.github.marad.cnd.city.actions._
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class Settings() extends Page {
  val cityActions = Seq(
    BrewPotions, BuildArmorer, BuildMagesGuild,
    LoversKiss, Raid, Taxes, WarMeeting
  )

  val cityActionsView = cityActions.map(action => div(
    Input.Number(action.cost)
  ))

  override def view(): View = div(cityActionsView)

  override def ready(route: InstantiatedRoute): Unit = {}
}
