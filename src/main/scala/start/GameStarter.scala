package start

import start.services.{GameManager, HeroFactory}

object GameStarter {

  def main(args: Array[String]): Unit = {

    val hero1 = HeroFactory.getRandomCharacter()
    val hero2 = HeroFactory.getRandomCharacter()

    GameManager.fight(hero1, hero2)

  }
}