package dto;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class FileInfo {
    public String name;
    public LocalDate createDate;
    
    public String fromPath;
    public String toPath;

    public File fromFile;
    public File toFile;

    public FileInfo(String name, LocalDate createDate, String fromPath, List<DirInfo> dirInfo) {
        this.name = name;
        this.createDate = createDate;
        this.fromPath = fromPath;

        // Path filePath = null;
        dirInfo.forEach(itm -> {
            //System.out.println(itm.stDate+" "+itm.edDate);
            if(itm.stDate!= null 
            && (createDate.isAfter(itm.stDate) || createDate.isEqual(itm.stDate))
            && (createDate.isBefore(itm.edDate) || createDate.isEqual(itm.edDate))
            ) {
                toPath = itm.getPath().toString();
            }else if(itm.stDate == null 
            && (createDate.isBefore(itm.edDate) || createDate.isEqual(itm.edDate))
            ) {
                toPath = itm.getPath().toString();
            }
        });


        fromFile = new File(new StringBuilder().append(fromPath).append(File.separator).append(name).toString());
        toFile = new File(new StringBuilder().append(toPath).append(File.separator).append(name).toString());

    }
}
