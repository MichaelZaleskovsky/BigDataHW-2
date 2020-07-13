package homework3.scala

import java.time.LocalDate
import java.util.stream.Collectors
import scala.collection.mutable
import scala.jdk.CollectionConverters._

object Friday13 {
  def main(args: Array[String]): Unit = {
    val startYear = 1975
    val endYear = 1985

    println("FRIDAY 13 - lists by years, in descending order")
    getFridaysList(startYear, endYear).foreach(x => println(x))

    println("FRIDAY 13 - quantity by years, in descending order")
    getFridaysNumberList(startYear, endYear).foreach(x => println(x))

  }

  def getFridaysNumberList(startYear: Int, endYear: Int): List[(Int, Int)] = {
    getFridaysList(startYear, endYear).
      map(entry => (entry._1, entry._2.size))
  }

  def getFridaysList(startYear: Int, endYear: Int): List[(Int, mutable.Buffer[LocalDate])] = {
    LocalDate.of(startYear, 1, 1).datesUntil(LocalDate.of(endYear+1, 1, 1)).
      filter((date: LocalDate) => date.getDayOfMonth == 13 && date.getDayOfWeek.getValue == 5).
      collect(Collectors.toList()).
      asScala.
      groupBy(date => date.getYear).
      toList.
      sortWith((ent1, ent2) => ent1._2.size > ent2._2.size)
  }
}
