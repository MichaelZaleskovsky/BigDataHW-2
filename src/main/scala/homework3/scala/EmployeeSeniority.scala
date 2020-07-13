package homework3.scala

import scala.util.Random

object EmployeeSeniority {
  val minSalary = 5000
  val maxSalary = 30000
  val EmploeeyNumber = 10

  object Seniority extends Enumeration {
    val JUNIOR, MIDDLE, SENIOR = Value
    type Seniority = Value
  }
  import Seniority._

  val employees = LazyList.range(0, EmploeeyNumber, 1).
    map(n => Employee("Employee"+Random.between(1000, 9999), Random.between(minSalary, maxSalary))).
    toList

  val seniorities: Array[(Seniority.Value, Employee => Boolean)] =
    Array(
      (JUNIOR, s => s.salary < 14000),
      (MIDDLE, s => s.salary >= 14000 && s.salary < 21000),
      (SENIOR, s => s.salary >= 21000)
    )

  def main(args: Array[String]): Unit = {
    seniorityMap.
      foreach(x => {print(x._1+" ["); x._2.foreach(y => print(y + ", ")); println("]")})

    employeesNumberMap.
      foreach(println)

    maxSalaryMap.
      foreach(println)

    totalSalaryMap.
      foreach(println)
  }

  def seniorityMap: Map[Value, Array[Employee]] = seniorities.
      flatMap(seniority => employees.filter(seniority._2).map(emp => (seniority._1, emp))).
      groupMap(tup => tup._1)(tup => tup._2)

  def employeesNumberMap: Map[Value, Int] = seniorityMap.map(tup => (tup._1, tup._2.length))

  def maxSalaryMap: Map[Value, Int] = seniorityMap.
      map(tup => (tup._1, tup._2.map(emp => emp.salary).max))

  def totalSalaryMap: Map[Value, Int] = seniorityMap.
      map(tup => (tup._1, tup._2.map(emp => emp.salary).sum))
}

case class Employee(name: String, salary: Int) {
}


