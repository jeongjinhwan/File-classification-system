package services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dto.dir.Dir;

public class AllFileList {

    private final static String FILE_ROOT_PATH = "C:\\Users\\solaa\\.gradle\\caches\\modules-2\\files-2.1";

    public List<Dir> dirList = new ArrayList<Dir>();

    public static void main(String[] ar) {
        AllFileList fileList = new AllFileList();
        fileList.setSubDir(FILE_ROOT_PATH);

        
    }

    private void setSubDir(String filePath) {
        File file = new File(filePath);
        File[] fileList = file.listFiles();

        try {
            for (File tmpFile : fileList) {
                // if (tmpFile.isFile()) {
                //     // jar 파일 만 그리고 sources.jar는 제외.
                //     // if (tmpFile.getName().contains(".jar") &&
                //     // !tmpFile.getName().contains("sources.jar")) {
                //     // System.out.println(tmpFile.getName().replaceAll(".jar", ""));
                //     // }
                // } else 
                if (tmpFile.isDirectory()) {
                    System.out.println(tmpFile.getCanonicalPath().toString());
                    dirList.add(new Dir(tmpFile.getCanonicalPath().toString()));
                    setSubDir(tmpFile.getCanonicalPath().toString()); // 재귀 호출
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
