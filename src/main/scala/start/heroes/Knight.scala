package start.heroes

import start.traits.Characters
import start.implicits.Skills._

import scala.util.Random

class Knight extends Characters {
  override var power: Int = Random.between(2, 13)
  override var hp: Int = Random.between(2, 13)

  override def kick(otherHero: Characters): Unit = {
    var getDamage: (Int => Int) = otherHero
//    var damage: Int = getDamage(power)
    println(s"KNIGHT: attack ${otherHero.getClass().getSimpleName} and took him ${getDamage(power)} power away")
  }
}
