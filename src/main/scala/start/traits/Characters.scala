package start.traits

trait Characters {
  var power: Int
  var hp: Int
  def kick(otherHero: Characters): Unit
  def isAlive: Boolean = hp > 0
}
