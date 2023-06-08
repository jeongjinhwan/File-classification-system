package dto;
import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        dirInfo.forEach(itm -> {
            if(itm.stDate!= null 
            && (createDate.isAfter(itm.stDate) || createDate.isEqual(itm.stDate))
            && (createDate.isBefore(itm.edDate) || createDate.isEqual(itm.edDate))
            ) {
                // System.out.println(ChronoUnit.DAYS.between(itm.stDate, itm.edDate));
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
