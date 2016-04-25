package main;

import cons.Const;

import java.util.Scanner;

/**
 * Created by Юлия on 22.04.2016.
 */
/**
 * Close program if get "stop"
 */
public class Butler extends Thread {

    public Butler() {
    }

    public void run() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.nextLine();
            if (string.toLowerCase().equals("stop now")) {
                System.exit(0);
            } else if (string.equals("stop")) {
                Const.working = false;
                //toDO safe close
                System.exit(0);
            }
        }
    }


}
