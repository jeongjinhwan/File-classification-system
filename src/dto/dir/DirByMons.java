package dto.dir;

import java.time.LocalDate;

/**
 * 월별 폴더 정보.
 */
public class DirByMons extends Dir {

    public LocalDate stDate;
    public LocalDate edDate;
    public int monCnt;

    public DirByMons(String path, LocalDate stDate, LocalDate edDate, int monCnt) {
        super(path);
        this.stDate = stDate;
        this.edDate = edDate;
        this.monCnt = monCnt;
    }
}
