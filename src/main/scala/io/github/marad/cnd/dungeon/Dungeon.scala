package io.github.marad.cnd.dungeon

case class Dungeon(
                  energy: Int = 20,
                  strength: Int = 0,
                  buildings: Seq[Building] = Seq()
                  )
