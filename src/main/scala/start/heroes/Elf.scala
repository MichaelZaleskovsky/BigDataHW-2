package start.heroes

import start.traits.Characters

class Elf extends Characters {
  override var power: Int = 10
  override var hp: Int = 10

  override def kick(otherHero: Characters): Unit = {
    var res = "";
    if (otherHero.power <= power) {
      otherHero.hp = 0
      res = "and kill him!"
    } else {
      otherHero.power = otherHero.power - 1
      res = "and took power from him"
    }
    println(s"ELF: attack ${otherHero.getClass().getSimpleName} $res")
  }
}
