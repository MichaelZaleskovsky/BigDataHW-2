package homework2.services

import org.reflections.Reflections
import homework2.traits.Characters

import scala.jdk.CollectionConverters._
import scala.util.Random

object HeroFactory {
  val reflections = new Reflections("start.heroes")
  val characterList = reflections.
    getSubTypesOf(classOf[Characters]).
    asScala.
    toList.
    map(cls => cls.getConstructor())

  def getRandomCharacter(): Characters = {
    val i = Random.between(0, characterList.size)
    characterList.apply(i).newInstance()
  }

}
