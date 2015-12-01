package io.github.marad.cnd.dungeon.actions

import io.github.marad.cnd.dungeon.{Dungeon, DungeonAction}
import org.widok._
import org.widok.bindings.Bootstrap._


object BuildCrystal extends DungeonAction {
  val cost: Var[Int] = Var(5)
  val duration: Var[Float] = Var(0.5f)
  val name: String = "Wybuduj Kryształ Energii"
  val crystalInc = Var(2)

  override def turnStartEffect(dungeon: Dungeon): Dungeon =
    dungeon.copy(crystals = dungeon.crystals + crystalInc.get)

  override val additionalSettings: View = Grid.Row(
    Grid.Column("Produkcja").offset(Size.ExtraSmall, 4).column(Size.ExtraSmall, 2),
    Grid.Column(
      Input.Number().bind(crystalInc.biMap(_.toString, _.toInt))
    ).column(Size.ExtraSmall, 2)
  )
}
