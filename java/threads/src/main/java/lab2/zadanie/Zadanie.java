package lab2.zadanie;

import java.util.*;

public class Zadanie {
    private final long liczba;
    private final List<Long> dzielniki = new ArrayList<>();

    public Zadanie(long liczba) { this.liczba = liczba; }

    public void sprawdzDzielniki() {
        for (long i = 1; i <= liczba; i++) {
            if (liczba % i == 0) {
                dzielniki.add(i);
            }
        }
    }

    // override zeby to wrzucic potem do tego writer'a
    @Override
    public String toString() {
        String temp = "";
        for (long liczb : dzielniki) {
            temp += liczb + ", ";
        }
        return (this.liczba + ": " + temp);
    }
}
