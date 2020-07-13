package homework3.java;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Friday13 {
    public static void main(String[] args) {
        int startYear = 1975;
        int endYear = 1985;

        System.out.println("FRIDAY 13 - lists by years, in descending order");
        getFridaysList(startYear, endYear).
                entrySet().
                forEach(System.out::println);

        System.out.println("FRIDAY 13 - quantity by years, in descending order");
        getFridaysNumberList(startYear, endYear).
                entrySet().
                forEach(System.out::println);
    }

    private static Map<Integer, List<LocalDate>> getFridaysList(int startYear, int endYear) {
// JAVA 8 Style
        return IntStream.rangeClosed(0, (endYear - startYear + 1) * 366).
                mapToObj(day -> LocalDate.of(startYear, 1, 1).plusDays(day)).
                filter(data -> data.getYear() <= endYear).
                filter(date -> date.getDayOfMonth() == 13 && date.getDayOfWeek().getValue() == 5).
                collect(groupingBy(date -> date.getYear())).
                entrySet().stream().
                sorted((entry1, entry2) -> entry2.getValue().size() - entry1.getValue().size()).
                collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(),
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static Map<Integer, Long> getFridaysNumberList(int startYear, int endYear) {
// JAVA 11
        return firstDay(startYear).datesUntil(firstDay(endYear + 1)).
                filter(date -> date.getDayOfMonth() == 13 && date.getDayOfWeek().getValue() == 5).
                collect(groupingBy(date -> date.getYear(), counting())).
                entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(),
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private static LocalDate firstDay(int year) {
        return LocalDate.of(year, 1, 1);
    }
}
