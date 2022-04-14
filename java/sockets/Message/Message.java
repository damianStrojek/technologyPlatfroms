package Message;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int[] number = new int[10];
    private String content;

    public Message(String s) {
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            this.number[i] = rand.nextInt(1000);
        }
        this.content = s;
    }

    public int[] getNumber() { return number; }

    public String getContent() { return content; }

    @Override
    public String toString() {
        return content + "\nZawartosc wiadomosci : " + number[0] + ", " + number[1] + ", " + number[2] + ", " +
        number[3] + ", " + number[4] + ", " + number[5] + ", " + number[6] + ", " + number[7] + ", " +
        number[8] + ", " + number[9] + ".";
    }
}
