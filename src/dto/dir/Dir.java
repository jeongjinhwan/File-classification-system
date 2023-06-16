package dto.dir;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 폴더 기본 정보.
 */
public class Dir {

    String name;
    String path;

    public Dir(String path) {
        this.path = path;
    }

    public Dir() {
    }

    public Path getPath() {
        return Paths.get(path);
    }
}