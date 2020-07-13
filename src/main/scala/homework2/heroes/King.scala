package homework2.heroes

import homework2.traits.Characters

import scala.util.Random
import homework2.implicits.Skills._

class King extends Characters {
  override var power: Int = Random.between(5, 16)
  override var hp: Int = Random.between(5, 16)

  override def kick(otherHero: Characters): Unit = {
    var getDamage: (Int => Int) = otherHero
    //    var damage: Int = getDamage(power)
    println(s"KING: attack ${otherHero.getClass().getSimpleName} and took him ${getDamage(power)} power away")
  }
}
