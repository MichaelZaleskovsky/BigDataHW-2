package homework2.implicits

import homework2.traits.Characters

import scala.util.Random

object Skills {
  implicit def skill(hero: Characters): Int => Int =
    power => {
      val damage = Random.between(1, power)
      hero.hp -= damage
      damage
    }
}
