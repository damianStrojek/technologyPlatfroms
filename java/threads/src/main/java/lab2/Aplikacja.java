package lab2;

import lab2.zadanie.ListaWynikowZadan;
import lab2.zadanie.ListaZadan;
import lab2.pracownik.Pracownik;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Aplikacja {
    public static void main(String[] args){
        int iloscWatkow = Integer.parseInt(args[0]);
        Scanner skaner = new Scanner(System.in);
        Scanner plikWejsciowy;
        boolean czyKoniec = false;

        final List<Thread> watki = new ArrayList<>();
        final ListaZadan listaZadan = new ListaZadan();
        final ListaWynikowZadan listaWynikowZadan = new ListaWynikowZadan();

        File plik = new File("src/main/java/lab2/test_1_watki.txt");
        try {
            plikWejsciowy = new Scanner(plik);
        } catch (FileNotFoundException except){
            System.out.println("Nie ma takiego pliku");
            return;
        }

        while(plikWejsciowy.hasNextLine()){
            listaZadan.dodaj(Long.parseUnsignedLong(plikWejsciowy.nextLine()));
        }
        plikWejsciowy.close();

        for (int i = 0; i < iloscWatkow; i++) {
            Thread watek = new Thread(new Pracownik(listaZadan, listaWynikowZadan));
            watki.add(watek);
            watek.start();
        }

        System.out.println("Menu :");
        System.out.println("1. Dodaj liczbe");
        System.out.println("2. Podaj wyniki");
        System.out.println("Podaj numer komendy : ");
        while(!czyKoniec && skaner.hasNext()){
            switch(skaner.next()){
                case "1":
                    System.out.print("Podaj liczbe do sprawdzenia : ");
                    long liczba = skaner.nextLong();
                    listaZadan.dodaj(liczba);
                    System.out.print("Podaj numer komendy : ");
                    break;
                case "2":
                    listaWynikowZadan.printResults();
                    break;
                case "exit":
                    czyKoniec = true;
                    System.out.println("Koniec watkow");
                    break;
                default:
                    System.out.print("Podałeś zły numer!");
                    System.out.print("Podaj numer komendy : ");
            }
        }
        // Posprzątanie po sobie
        skaner.close();
        for(Thread watek : watki){
            watek.interrupt();
        }
        // Wpisywanie do pliku
        try {
            FileWriter wpisywacz = new FileWriter(new File("src/main/java/lab2/Wynik1.txt").getAbsolutePath(), false);
            listaWynikowZadan.printResults(wpisywacz);
            wpisywacz.close();
        } catch (IOException except){
            return;
        }
    }
}
