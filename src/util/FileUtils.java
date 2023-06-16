package util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    private static final String SOURCES = "sources";

    /**
     * 해당 디렉토리 경로의 모든 파일을 가지고 옵니다.
     * 대상 확장자는 exex 입니다.
     * 
     * @param filePath
     * @param exe
     * @return
     */
    public static List<String> getFileNameList(String filePath, String exe) {
        return Arrays.asList(new File(filePath).listFiles()).stream().map(itm -> {
            String fileNm = null;
            if (itm.isFile()) {
                if (itm.getName().contains(exe)
                        && !itm.getName().contains(new StringBuilder().append(SOURCES).append(exe).toString())) {
                    fileNm = itm.getName().replaceAll(exe, "");
                }
            }
            return fileNm;
        }).filter(itm -> itm != null).collect(Collectors.toList());
    }
}
