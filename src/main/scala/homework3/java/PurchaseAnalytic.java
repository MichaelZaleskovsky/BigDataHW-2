package homework3.java;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class PurchaseAnalytic {
    @Data
    @AllArgsConstructor
    static class Product {
        String name;
        int price;
    }

    @Data
    @AllArgsConstructor
    static class Purchase {
        String customer;
        Product product;
    }

    public static void main(String[] args) {
        String[] customersList = {"Robert", "Anna", "Sarah", "Moshe", "Benjamin", "Anat", "Dan", "Mark", "Sofia"};

        Product[] productList = {
                new Product("Salt", 10),
                new Product("Sugar", 30),
                new Product("Oil", 35),
                new Product("Bread", 15),
                new Product("Flour", 22),
                new Product("Milk", 11),
                new Product("Tea", 28),
                new Product("Coffee", 39),
                new Product("Cheese", 42),
                new Product("Sausage", 51)
        };

        Random rnd = new Random();
        int purchasesNumber = 200;

// GENERATE RANDOM PURCHASES LIST
        List<Purchase> purchases = rnd.ints(purchasesNumber).
                mapToObj(n -> new Purchase(customersList[rnd.nextInt(customersList.length)], productList[rnd.nextInt(productList.length)])).
                collect(Collectors.toList());

        getCustomersWithList(purchases).
                entrySet().
                forEach(System.out::println);

        getCustomersWithSum(purchases).
                entrySet().
                forEach(System.out::println);

        getCustomersWithAverage(purchases).
                entrySet().
                forEach(System.out::println);
    }

    static Map<String, Double> getCustomersWithAverage(List<Purchase> purchases) {
        return purchases.stream().
                collect(Collectors.groupingBy(p -> p.getCustomer(), Collectors.averagingInt(p -> p.getProduct().getPrice())));
    }

    static Map<String, Integer> getCustomersWithSum(List<Purchase> purchases) {
        return purchases.stream().
                collect(Collectors.groupingBy(p -> p.getCustomer(), Collectors.summingInt(p -> p.getProduct().getPrice())));
    }

    static Map<String, List<Purchase>> getCustomersWithList(List<Purchase> purchases) {
        return purchases.stream().
                collect(Collectors.groupingBy(p -> p.getCustomer()));
    }
}
