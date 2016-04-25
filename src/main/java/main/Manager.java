package main;

import cons.Const;
import fileswork.FileHandle;
import watcher.DirChangeListener;
import psevdo_BD.Storage;
import java.nio.file.Path;

import watcher.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Юлия on 24.04.2016.
 */
/**
 * Catch events from WatchDir
 */
public class Manager implements DirChangeListener {
    Storage map;
    private WatchDir watcher;
    ExecutorService executor;

    public Manager(WatchDir watcher) throws IOException {

        this.map = new Storage();
        this.watcher = watcher;
        watcher.addListener(this);
        executor = Executors.newFixedThreadPool(Const.threadsCount);

    }

    public void handleFile(Path file) {
        if (file != null && Const.working) {
            FileHandle fileHandler = new FileHandle(file, map);
            executor.submit(fileHandler);
        }

    }

}



