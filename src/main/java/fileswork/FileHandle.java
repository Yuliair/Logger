package fileswork;

/**
 * Created by Юлия on 23.04.2016.
 */
import psevdo_BD.Storage;

import java.io.*;
import java.nio.file.Path;


public class FileHandle implements Runnable{
    Path file;
    Storage storage;


    public FileHandle(Path file, Storage mapMy) {
        this.file = file;
        this.storage = mapMy;
    }
    /**
     * Read file and give date to storage
     */
    public void run(){
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(String.valueOf(file)));
            while ((line = br.readLine()) != null) {
                storage.put(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}