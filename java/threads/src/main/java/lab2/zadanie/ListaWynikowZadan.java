package lab2.zadanie;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class ListaWynikowZadan {
    private final List<Zadanie> wyniki = new ArrayList<>();

    public synchronized void dodajWynik(Zadanie wynik) { wyniki.add(wynik); }

    public synchronized void printResults() {
        for (Zadanie zadanie : wyniki) {
            System.out.println(zadanie);
        }
    }

    public synchronized void printResults(FileWriter writer) {
        for (Zadanie zadanie : wyniki) {
            try {
                writer.write(zadanie.toString());
                // bo nie robimy println
                writer.write(System.lineSeparator());
            } catch (IOException except) {
                break;
            }
        }
    }

}
