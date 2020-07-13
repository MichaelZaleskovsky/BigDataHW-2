package homework3.scala

import scala.util.Random

object PurchaseAnalytic {

  case class Product(name: String, price: Int){}

  case class Purchase(customer: String, product: Product){}

  val customersList: Array[String] = Array("Robert", "Anna", "Sarah", "Moshe", "Benjamin", "Anat", "Dan", "Mark", "Sofia")

  val productList: Array[Product] = Array(
    Product("Salt", 10),
    Product("Sugar", 30),
    Product("Oil", 35),
    Product("Bread", 15),
    Product("Flour", 22),
    Product("Milk", 11),
    Product("Tea", 28),
    Product("Coffee", 39),
    Product("Cheese", 42),
    Product("Sausage", 51))

  val purchasesNumber = 50

// GENERATE RANDOM PURCHASES LIST
  val purchases = LazyList.range(0, purchasesNumber, 1).
    map(n => Purchase(customersList(Random.nextInt(customersList.length)), productList(Random.nextInt(productList.length)))).
    toList

  def main(args: Array[String]): Unit = {

    getCustomersWithList.foreach(println)

    getCustomersWithSum.foreach(println)

    getCustomersWithAverage.foreach(println)

  }

  def getCustomersWithList: Map[String, List[Product]] = purchases.
    groupMap(p => p.customer)(p => p.product)

  def getCustomersWithSum: Map[String, Int] = purchases.
    groupMap(p => p.customer)(p => p.product.price).map(tup => (tup._1, tup._2.sum))

  def getCustomersWithAverage: Map[String, Float] = purchases.
    groupMap(p => p.customer)(p => p.product.price).map(tup => (tup._1, tup._2.sum.asInstanceOf[Float]/tup._2.length))

}
