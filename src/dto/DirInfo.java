package dto;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class DirInfo {

    LocalDate stDate;
    LocalDate edDate;
    int monCnt;
    String fullName;
    String rootPath;

    public DirInfo(String rootPath, LocalDate stDate, LocalDate edDate, int monCnt) {
        this.rootPath = rootPath;
        this.stDate = stDate;
        this.edDate = edDate;
        this.monCnt = monCnt;
    }

    public String getFullName() {
        return new StringBuilder()
                .append(monCnt + "개월 ")
                .append("(")
                .append(stDate == null ? "" : stDate.toString())
                .append(" ~ ")
                .append(edDate.toString())
                .append(")")
                .toString();
    }

    public Path getPath() {
        return Paths.get(new StringBuilder()
                .append(rootPath)
                .append(File.separator)
                .append(getFullName())
                .toString());
    }

}