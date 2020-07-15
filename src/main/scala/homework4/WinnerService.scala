package homework4

object WinnerService {
  case class Bet(name: String, summ: Int)

  val players1: List[Option[Bet]] = List(
    Some(Bet("Moshe", 15)),
    Some(Bet("Benjamin", 20)),
    Option.empty,
    Some(Bet("Anat", 8)),
    Some(Bet("Mark", 12))
  )

  val spectators1 = List(
    Some(Bet("John", 15)),
    Some(Bet("Anna", 20)),
    Option.empty,
    Some(Bet("Jim", 8)),
    Some(Bet("Donald", 12))
  )

  val players2 = List(Option.empty, Option.empty)

  val spectators2 = List(Option.empty, Option.empty)

  def main(args: Array[String]): Unit = {
    println(getWinner(players1, spectators1)) // Benjamin, 110
    println(getWinner(players2, spectators1)) // Anna, 55
    println(getWinner(players2, spectators2)) // No winners, 0

  }

  def getMax(players: List[Option[Bet]]): Option[Bet] = {
    players.max(Ordering.by[Option[Bet], Int](
      x => x.getOrElse(Bet("", 0)).summ))
  }

  def getWinner(players: List[Option[Bet]], spectators: List[Option[Bet]]): (String, Int) = {
    val name = getMax(players).orElse(getMax(spectators)).getOrElse(Bet("No winners", 0)).name
    val prize = (players ::: spectators).map(opt => opt.getOrElse(Bet("", 0)).summ).sum
    (name, prize)
  }
}
