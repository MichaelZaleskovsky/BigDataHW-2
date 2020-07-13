package homework3.java;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class EmployeeSeniority {
    enum Seniority {
        JUNIOR(s -> s.getSalary() < 14000),
        MIDDLE(s -> s.getSalary() >= 14000 && s.getSalary() < 21000),
        SENIOR(s -> s.getSalary() >= 21000);

        private Predicate<Employee> predicate;

        Seniority(Predicate<Employee> predicate) {
            this.predicate = predicate;
        }

        public Predicate<Employee> getPredicate(){
            return predicate;
        }
    }

    @Data
    @AllArgsConstructor
    static class Pair {
        Seniority key;
        Employee value;
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        int minSalary = 5000;
        int maxSalary = 30000;
        int EmploeeyNumber = 5;

// Generate List of Emploee for test
        List<Employee> employees = rnd.ints(minSalary, maxSalary).
                mapToObj(i -> new Employee(("Emploee" + (rnd.nextInt(9999) + 1000)), i)).
                limit(EmploeeyNumber).collect(Collectors.toList());

        List<Seniority> seniorities = List.of(Seniority.JUNIOR, Seniority.MIDDLE, Seniority.SENIOR);

// Multimap of Lists of Employees by Seniority
        getSeniorityMap(employees, seniorities).entrySet().
                forEach(System.out::println);

// Numbers of Employees by Seniority
        getEmployeesNumberMap(employees, seniorities).
                entrySet().forEach(System.out::println);

// Max salalry by Seniority
        getMaxSalaryMap(employees, seniorities).
                entrySet().forEach(System.out::println);

// Total salalry by Seniority
        getTotalSalaryMap(employees, seniorities).
                entrySet().forEach(System.out::println);
    }

    private static Map<Seniority, Integer> getTotalSalaryMap(List<Employee> employees, List<Seniority> seniorities) {
        return getSeniorityMap(employees, seniorities).
                entrySet().stream().
                collect(Collectors.toMap(Map.Entry::getKey,
                                            p -> p.getValue().stream().mapToInt(emp -> emp.getSalary()).sum()));
    }

    private static Map<Seniority, Integer> getMaxSalaryMap(List<Employee> employees, List<Seniority> seniorities) {
        return getSeniorityMap(employees, seniorities).
                entrySet().stream().
                collect(Collectors.toMap(Map.Entry::getKey,
                                            p -> p.getValue().stream().mapToInt(emp -> emp.getSalary()).max().orElse(0)));
    }

    private static Map<Seniority, Integer> getEmployeesNumberMap(List<Employee> employees, List<Seniority> seniorities) {
        return getSeniorityMap(employees, seniorities).
                entrySet().stream().
                collect(Collectors.toMap(Map.Entry::getKey, p -> p.getValue().size()));
    }

    private static Map<Seniority, List<Employee>> getSeniorityMap(List<Employee> employees, List<Seniority> seniorities) {
        return seniorities.stream().
                flatMap(s -> employees.stream().
                        filter(s.getPredicate()).
                        map(emp -> new Pair(s, emp))
                ).collect(groupingBy(pair -> pair.getKey(), getCollector()));
    }

    private static Collector<Pair, List<Employee>, List<Employee>> getCollector() {
            return Collector.of(
                    () -> new ArrayList(),
                    (list, pair) -> list.add(pair.getValue()),
                    (left, right) -> { left.addAll(right); return left;},
                    Collector.Characteristics.IDENTITY_FINISH);
    }


}
