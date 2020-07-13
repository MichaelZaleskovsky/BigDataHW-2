package homework2.heroes

import homework2.traits.Characters

class Hobbit extends Characters{
  override var power: Int = 0
  override var hp: Int = 3

  override def kick(otherHero: Characters): Unit = {
    println(s"HOBBIT: I will kill you forever, ugly ${otherHero.getClass().getSimpleName.toUpperCase}!")
  }

}
