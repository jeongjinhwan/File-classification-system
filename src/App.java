import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.directory.BasicAttribute;

import dto.DirInfo;
import dto.FileInfo;

public class App {

    private static String ROOT_PATH = "D:\\test1";

    // private static
    private static String IMAGE_ROOT_PATH = "D:\\test2";

    public static void main(String[] args) throws Exception {
        
        //파일 디렉토리를 생성합니다.
        List<DirInfo> pathList = GeneratedDirectoryPathList();
        boolean sucess = GeneratedDirectory(pathList);

        pathList.forEach(itm->{
            System.out.println(itm.getFullName());
        });

        // sucess = false;
        System.out.println(sucess);
        if(!sucess){
            System.out.println("경로 생성에 문제가 발생하였습니다.");
            return;
        }


        //파일 목록을 읽어 옵니다.
        List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
        for (String fileNm : new File(IMAGE_ROOT_PATH).list()) {
            BasicFileAttributes tmp2 = Files.readAttributes( Paths.get(IMAGE_ROOT_PATH + File.separator + fileNm)
                                                           , BasicFileAttributes.class);

            LocalDate localDate = new Date(tmp2.lastModifiedTime().toMillis())
                                        .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            fileInfoList.add(new FileInfo(fileNm, localDate, IMAGE_ROOT_PATH, pathList));
        }


        //파일 이동.
        fileInfoList.forEach(itm -> {
            System.out.println(itm.name + " " + itm.createDate + " " + itm.fromPath + " 에서  " + itm.toPath + " 여기로");
            System.out.println(itm.name + " " + itm.createDate + " " + itm.fromFile + " 에서  " + itm.toFile + " 여기로");
            try {
                moveFile(itm.fromFile, itm.toFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static void moveFile(File src, File dest) throws IOException {
        Files.move(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private static List<DirInfo> GeneratedDirectoryPathList() {
        LocalDate rootDate = LocalDate.of(2021, 8, 13);
        List<DirInfo> pathList = new ArrayList<DirInfo>();

        LocalDate stDate = null, edDate;

        for (int i = 0; i < 24; i++) {
            edDate = rootDate.plusMonths(i).minusDays(1);
            pathList.add(new DirInfo(ROOT_PATH, stDate, edDate, i));
            stDate = rootDate.plusMonths(i);
        }

        return pathList;
    }

    private static boolean GeneratedDirectory(List<DirInfo> fileList) {
        boolean result = false;
        try {
            fileList.forEach(path -> {
                try {
                    Files.createDirectories(path.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
