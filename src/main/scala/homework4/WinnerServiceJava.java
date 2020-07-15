package homework4;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinnerServiceJava {

    @Data
    @AllArgsConstructor
    static class Pair {
        String name;
        int prize;
    }

    @Data
    @AllArgsConstructor
    static class Bet {
        String name;
        int summ;
        public Bet(){
            name = "";
            summ = 0;
        }
    }

    public static void main(String[] args) {
        List<Optional<Bet>> players1 = List.of(
                Optional.of(new Bet("Moshe", 10)),
                Optional.of(new Bet("Benjamin", 20)),
                Optional.empty(),
                Optional.of(new Bet("Anat", 8)),
                Optional.of(new Bet("Mark", 12))
        );

        List<Optional<Bet>> spectators1 = List.of(
                Optional.of(new Bet("John", 10)),
                Optional.of(new Bet("Anna", 20)),
                Optional.empty(),
                Optional.of(new Bet("Jim", 8)),
                Optional.of(new Bet("Donald", 12))
        );

        List<Optional<Bet>> players2 = List.of(Optional.empty(), Optional.empty());

        List<Optional<Bet>> spectators2 = List.of(Optional.empty(), Optional.empty());

        System.out.println(getWinner(players1, spectators1)); // Benjamin, 100
        System.out.println(getWinner(players2, spectators1)); // Anna, 50
        System.out.println(getWinner(players2, spectators2)); // No winners, 0

    }

    private static Pair getWinner(List<Optional<Bet>> players, List<Optional<Bet>> spectators) {
        String name = getMax(players).
            or(() -> getMax(spectators)).
            orElseGet(Bet::new).
            getName();

        int prize = Stream.concat(players.stream(), spectators.stream()).
            mapToInt(opt -> opt.orElse(new Bet()).getSumm()).sum();

        return new Pair (name, prize);
    }

    private static Optional<Bet> getMax(List<Optional<Bet>> players) {
        return players.
                stream().
                max((a, b) -> a.orElseGet(Bet::new).summ - b.orElseGet(Bet::new).summ).
                get();
    }
}
