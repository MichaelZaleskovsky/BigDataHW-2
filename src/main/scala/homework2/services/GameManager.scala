package homework2.services

import homework2.heroes.Hobbit
import homework2.traits.Characters

object GameManager {
  def fight(h1: Characters, h2: Characters): Unit = {
    var hero: Characters = null
    var hero1 = h1
    var hero2 = h2
    if (hero1.getClass.equals(classOf[Hobbit]) && hero2.getClass.equals(classOf[Hobbit])) {
      println("Hobbits don't fight each other!")
    } else {
      println(s"Battle between ${hero1.getClass.getSimpleName} and ${hero2.getClass.getSimpleName}")

      while (hero1.isAlive && hero2.isAlive) {
        hero1.kick(hero2)
        hero = hero1
        hero1 = hero2
        hero2 = hero
      }

      println("Battle finised!")
      var winner = if (hero1.isAlive)  hero1.getClass.getSimpleName else hero2.getClass.getSimpleName
      println(s"The WINNER is $winner")
    }
  }
}
