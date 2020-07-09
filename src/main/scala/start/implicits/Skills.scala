package start.implicits

import start.traits.Characters

import scala.util.Random

object Skills {
  implicit def skill(hero: Characters): Int => Int =
    power => {
      val damage = Random.between(1, power)
      hero.hp -= damage
      damage
    }
}
