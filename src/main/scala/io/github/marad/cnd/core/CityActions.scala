package io.github.marad.cnd.core

import io.github.marad.cnd.city.actions._

trait CityActions {
  val cityActions = Seq(
    BrewPotions, BuildArmorer, BuildMagesGuild,
    LoversKiss, Raid, Taxes, WarMeeting)
}
