package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 싱글턴.
 */
public class DirUtils {

    private static DirUtils dirUtils;

    private DirUtils() {
    }

    public static DirUtils getInstance() {
        return (dirUtils == null) ? dirUtils = new DirUtils() : dirUtils;
    }

    private List<String> dirList = new ArrayList<>();

    /**
     * 경로의 마지막 주소를 가지고 옵니다.
     * @param path
     * @return
     */
    public String getLastDirName(String path) {
        Matcher matcher = Pattern.compile("([a-zA-Z_0-9.-]+)$").matcher(path);
        return matcher.find() ? matcher.group() : "";
    }

    public DirUtils cleartDirList() {
        dirList.clear();
        return getInstance();
    }

    public DirUtils listFiles(File dir) {
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                dirList.add(file.getAbsolutePath());
                listFiles(file);
            }
        }
        return getInstance();
    }

    public List<String> getDirList() {
        return dirList;
    }

}
