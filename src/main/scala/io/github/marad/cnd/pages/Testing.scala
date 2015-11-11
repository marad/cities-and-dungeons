package io.github.marad.cnd.pages

import io.github.marad.cnd.Game
import io.github.marad.cnd.city.actions._
import io.github.marad.cnd.widgets.CityView
import org.widok._
import org.widok.html._
import org.widok.bindings.Bootstrap._

case class Testing() extends Page {

  val dayTime = Var(1f)
  val nightTime = Var(1f)
  val cityActions = Seq(BrewPotions, BuildArmorer, BuildMagesGuild, LoversKiss, Raid, Taxes, WarMeeting)


  val actionsView = div(
    cityActions.map(action => {
      Button(action.name, " | ", action.cost, "zł | Czas:", action.duration)
        .onClick(_ => {
          Game.city := action(Game.city.get)
          dayTime.update(_ - action.duration)
        })
        .enabled(Game.city.zip(dayTime).map(v => action.cost <= v._1.gold && action.duration <= v._2))
        .width(Length.Percentage(1))
    }),
    Button("Koniec dnia")
      .onClick(_ => {
        Game.city.get.buildings.foreach { building =>
          Game.city := building(Game.city.get)
        }
        dayTime := 1f
      })
  )

  override def view(): View = div(
    Grid.Row(
      Grid.Column("Dzień: ", dayTime).column(Size.ExtraSmall, 1),
      Grid.Column(CityView(Game.city)).column(Size.ExtraSmall, 7),
      Grid.Column(actionsView).column(Size.ExtraSmall, 4)
    ),
    Grid.Row(
      Grid.Column("Noc: ", nightTime).column(Size.ExtraSmall, 1),
      Grid.Column("dungeon view").column(Size.ExtraSmall, 7),
      Grid.Column("actions").column(Size.ExtraSmall, 4)
    )
  )

  override def ready(route: InstantiatedRoute): Unit = {}
}
