package lab2.pracownik;

import java.lang.Thread;
import lab2.zadanie.Zadanie;
import lab2.zadanie.ListaWynikowZadan;
import lab2.zadanie.ListaZadan;

// The Runnable interface should be implemented by any class
// whose instances are intended to be executed by a thread.
public class Pracownik implements Runnable {
    private final ListaZadan listaZadan;
    private final ListaWynikowZadan listaWynikowZadan;

    public Pracownik(ListaZadan listaZadan, ListaWynikowZadan listaWynikowZadan){
        this.listaWynikowZadan = listaWynikowZadan;
        this.listaZadan = listaZadan;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try {
                Zadanie zadanie = listaZadan.get();
                //System.out.println("Numer watku : " + Thread.currentThread());
                Thread.sleep(500); // opoznienie bo za szybko szlo
                zadanie.sprawdzDzielniki();
                listaWynikowZadan.dodajWynik(zadanie); // z tego zadania sobie bierze tą liste wynikow
            } catch (InterruptedException except){
                break;
            }
        }
    }

}
