package services;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import dto.FileInfo;
import dto.StartDate;
import dto.dir.DirByMons;
import util.LocalDateUtils;

/**
 * 디렉토리 생성 시 이미 있는 경우 아무것도 하지 않습니다.
 * 파일 이동 시 이름이 같은 경우 덮어습니다.
 */
public class ClassifiedByMonth {

    private static String ROOT_PATH = "C:\\test1";
    private static String IMAGE_ROOT_PATH = "C:\\test2";
    private static int DATE_YEAR = 2021;
    private static int DATE_MON = 8;
    private static int DATE_DAY = 13;

    public static void main(String[] args) throws Exception {

        // 파일 디렉토리 정보 생성.
        List<DirByMons> pathList = GeneratedDirectoryPathList(new StartDate(DATE_YEAR, DATE_MON, DATE_DAY));
        // 파일 디렉토리 생성.
        boolean sucess = GeneratedDirectory(pathList);

        if (!sucess) {
            System.out.println("경로 생성에 문제가 발생하였습니다.");
            return;
        }

        // 파일 목록 생성.
        List<Path> fielList = Files.list(Paths.get(IMAGE_ROOT_PATH)).toList();
        if (fielList.size() < 1) {
            System.out.println("대상 파일이 없습니다.");
            return;
        }

        // 파일 추가 정보 입력.
        List<FileInfo> fileInfoList = getListFileInfo(fielList, pathList);

        // 파일 이동.
        moveList(fileInfoList);
    }



    /**
     * 파일을 이동 시킨다.
     * 
     * @param fileInfoList
     */
    private static void moveList(List<FileInfo> fileInfoList) {
        fileInfoList.forEach(itm -> {
            try {
                moveFile(itm.fromFile, itm.toFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("파일 이동 완료.");
    }

    private static void moveFile(File src, File dest) throws IOException {
        Files.move(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 파일 정보를 세팅합니다.
     * 
     * @param fielList
     * @param pathList
     * @return
     */
    private static List<FileInfo> getListFileInfo(List<Path> fielList, List<DirByMons> pathList) {
        return fielList.stream().map(itm -> {
            // 최종 수정일자 정보를 가지고 옵니다.
            try {
                BasicFileAttributes attrs = Files.readAttributes(itm, BasicFileAttributes.class);
                LocalDate localDate = new Date(attrs.lastModifiedTime().toMillis()).toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();

                String toPath = pathList.stream().map(path -> {
                    if (path.stDate != null
                            && LocalDateUtils.isEqualAndAfter(localDate, path.stDate)
                            && LocalDateUtils.isEqualAndBefore(localDate, path.edDate)) {
                        return path.getPath().toString();
                    } else if (path.stDate == null
                            && LocalDateUtils.isEqualAndBefore(localDate, path.edDate)) {
                        return path.getPath().toString();
                    }
                    return "";
                }).collect(Collectors.joining());

                return new FileInfo(itm.getFileName().toString(), localDate, IMAGE_ROOT_PATH, toPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    /**
     * 폴더의 경로 정보를 입력해 줍니다.
     * 
     * @param startDate
     * @return
     */
    private static List<DirByMons> GeneratedDirectoryPathList(StartDate startDate) {
        LocalDate rootDate = LocalDate.of(startDate.year, startDate.mon, startDate.day);
        long maxPathCnt = ChronoUnit.MONTHS.between(rootDate, LocalDate.now()) + 2;
        List<DirByMons> pathList = new ArrayList<DirByMons>();

        LocalDate stDate = null, edDate;

        for (int i = 0; i < maxPathCnt; i++) {
            edDate = rootDate.plusMonths(i).minusDays(1);
            pathList.add(new DirByMons(getDirName(stDate, edDate, i), stDate, edDate, i));
            stDate = rootDate.plusMonths(i);
        }
        System.out.println("파일 정보 생성 완료");
        return pathList;
    }

    /**
     * 폴더 경로를 생성 합니다.
     * 이미 생성된 경우 아무것도 하지 않습니다.
     * 
     * @param fileList
     * @return
     */
    private static boolean GeneratedDirectory(List<DirByMons> fileList) {
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
            System.out.println("디렉토리 경로 생성완료.");
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    private static String getDirName(LocalDate stDate, LocalDate edDate, int i){
        return new StringBuilder()
                    .append(ROOT_PATH)
                    .append(File.separator)
                    .append(i + "개월 ")
                    .append("(")
                    .append(stDate == null ? "" : stDate)
                    .append(" ~ ")
                    .append(edDate)
                    .append(")")
                    .toString();
    }
}
