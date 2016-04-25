package watcher;

import java.nio.file.Path;

public interface DirChangeListener {
    void handleFile(Path file);
}
