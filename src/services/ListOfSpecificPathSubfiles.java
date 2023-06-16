package services;

import java.io.File;

import util.DirUtils;
import util.FileUtils;

/**
 * 특정 경로 하위 파일 목록.
 */
public class ListOfSpecificPathSubfiles {

    private static final String FILE_ROOT_PATH = "C:\\Users\\solaa\\.gradle\\caches\\modules-2\\files-2.1";
    private static final String EXE = ".jar";

    public static void main(String[] ar) {
        new ListOfSpecificPathSubfiles().getFiles(FILE_ROOT_PATH, EXE);
    }

    /**
     * 파일 목록을 출력 합니다.
     */
    private void getFiles(String rootPath, String exe) {
        DirUtils.getInstance().listFiles(new File(rootPath)).getDirList().forEach(filePath -> {
            FileUtils.getFileNameList(filePath, exe).forEach(itm -> {
                System.out.println(itm);
            });
        });
    }
}
