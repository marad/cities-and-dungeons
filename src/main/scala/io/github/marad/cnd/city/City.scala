package io.github.marad.cnd.city

case class City(
                 gold: Int = 30,
                 preparation: Int = 0,
                 hero: Hero = Hero(),
                 buildings: Seq[Building] = Seq()
               )
