package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirUtils {

    public static String getLastDirName(String path){
        Matcher matcher = Pattern.compile("([a-zA-Z_0-9.-]+)$").matcher(path);
        return matcher.find() ? matcher.group() : "";
    }
}
