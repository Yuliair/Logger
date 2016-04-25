package main;

import cons.Const;
import watcher.WatchDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by Юлия on 25.04.2016.
 */
public class Starter {
    public Starter() {


    }

    /**
     * Create necessary objects
     */
    public void start() {
        Butler butler = new Butler();
        butler.start();
        WatchDir watcher;
        try {
            watcher = new WatchDir(Paths.get(Const.folderFileIn));
            Manager manager = new Manager(watcher);
            File myFolder = new File(Const.folderFileIn);
            watcher.start();
            File[] files = myFolder.listFiles();

            if (files != null) {
                for (File file : files) {
                    manager.handleFile(Paths.get(file.getPath()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the property value and fill Const with names of output and input dir.
     */
    public void fillConst() throws IOException {

        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            String output = prop.getProperty("output");
            Const.setfolderFileOut(output);
            String input = prop.getProperty("input");
            Const.setfolderFileIn(input);
            String fileOut = prop.getProperty("fileOut");
            Const.setFileOut(fileOut);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null)
                inputStream.close();
        }
    }
}
