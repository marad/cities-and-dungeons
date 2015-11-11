package io.github.marad.cnd.city

import io.github.marad.cnd.Building

case class City(
                 gold: Int = 30,
                 preparation: Int = 0,
                 hero: Hero = Hero(),
                 buildings: Seq[Building[City]] = Seq()
               ) {
  def beginTurn(): City =
    buildings.foldLeft[City => City](identity)(
      (f, b) => f andThen b.onTurnStart
    )(this)

  def endTurn(): City =
    buildings.foldLeft[City => City](identity)(
      (f, b) => f andThen b.onTurnEnd
    )(this)
}
