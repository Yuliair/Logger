package main;

import java.io.*;

/**
 * Created by Юлия on 24.04.2016.
 */
public class Main {
    public static void main(String[] args) {

        Starter starter = new Starter();
        try {
            starter.fillConst();
            starter.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

