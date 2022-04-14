package lab2.zadanie;

import java.util.ArrayList;
import java.util.List;

public class ListaZadan {
    private final List<Zadanie> zadania = new ArrayList<>();

    // A piece of logic marked with synchronized becomes
    // a synchronized block, allowing only one thread to execute at any given time.
    public synchronized void dodaj(Long wartosc){
        zadania.add(new Zadanie(wartosc));
        // It wakes up all the threads that called wait() on the same object.
        // The highest priority thread will run first in most of the situation, though not guaranteed.
        notifyAll();
    }

    public synchronized Zadanie get() throws InterruptedException {
        while(zadania.isEmpty()){
            wait();
        }
        return zadania.remove(0);
    }
}
