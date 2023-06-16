package dto;

import java.io.File;
import java.time.LocalDate;

public class FileInfo {
    public String name;
    public LocalDate createDate;

    public String fromPath;
    public String toPath;

    public File fromFile;
    public File toFile;

    public FileInfo(String name, LocalDate createDate, String fromPath, String toPath) {
        this.name = name;
        this.createDate = createDate;
        this.fromPath = fromPath;
        this.toPath = toPath;
        fromFile = new File(new StringBuilder().append(fromPath).append(File.separator).append(name).toString());
        toFile = new File(new StringBuilder().append(toPath).append(File.separator).append(name).toString());
    }
}
