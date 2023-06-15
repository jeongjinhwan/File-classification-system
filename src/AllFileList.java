import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllFileList {
    public static void main(String[] ar){
        new AllFileList().getAllFileNamesUnder();

        // System.out.println("java-jwt-4.4.0-sources.jar".contains(".ja1r"));
    }

    private List<String> getAllFileNamesUnder(){
        List<String> retList = new ArrayList<String>();

        String filePath = "C:\\Users\\solaa\\.gradle\\caches\\modules-2\\files-2.1";
        subDirList(filePath);

        return retList;
    }

    public static void subDirList(String filePath){
 
        File file = new File(filePath); 
        File[] fileList = file.listFiles();
 
        try{
            for(File tmpFile : fileList){
                if(tmpFile.isFile()){
                    // System.out.println("파일 = " + filePath+"/"+tmpFile.getName());
                    if(tmpFile.getName().contains(".jar") && !tmpFile.getName().contains("sources.jar")){
                        System.out.println(tmpFile.getName().replaceAll(".jar", ""));
                    }
                    // if("jar".equalsIgnoreCase(exe)){
                    //     System.out.println(tmpFile.getName());
                    // }
                }else if(tmpFile.isDirectory()){
                    //System.out.println("디렉토리 = " + filePath+"/"+tmpFile.getName());
                    subDirList(tmpFile.getCanonicalPath().toString()); //재귀 호출
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
