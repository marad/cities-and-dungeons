package io.github.marad.cnd.dungeon.buildings

import io.github.marad.cnd.Building
import io.github.marad.cnd.dungeon.Dungeon
import org.widok._
import org.widok.bindings.Bootstrap._

class IllusionNet extends Building[Dungeon] {
  override val name: String = IllusionNet.name
  override val cost: Var[Int] = IllusionNet.cost
  override val buildTime: Var[Float] = IllusionNet.buildTime

  private var turnsLeft = IllusionNet.activeTurns.get

  override def onTurnEnd(t: Dungeon): Dungeon =
    if (turnsLeft > 0) {
      turnsLeft -= 1
      t
    } else {
      t.destroy(this)
    }
}

object IllusionNet {
  val name: String = "Siatka Iluzji"
  val cost: Var[Int] = Var(20)
  val buildTime: Var[Float] = Var(0)
  val activeTurns = Var(2)

  val additionalSettings: View = Grid.Row(
    Grid.Column("Czas Trwania (tury)").offset(Size.ExtraSmall, 4).column(Size.ExtraSmall, 2),
    Grid.Column(
      Input.Number().bind(activeTurns.biMap(_.toString, _.toInt))
    ).column(Size.ExtraSmall, 2)
  )
}