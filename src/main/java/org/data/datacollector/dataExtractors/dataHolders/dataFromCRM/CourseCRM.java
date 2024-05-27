package org.data.datacollector.dataExtractors.dataHolders.dataFromCRM;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.data.datacollector.dataExtractors.globalDataHolders.CourseData;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelSheet("CRM")
public class CourseCRM extends CourseData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("date_CTL1")
    private String courseDate1;

    @ExcelCellName("Mle 1")
    private String instructorNumber1;

    @ExcelCellName("date_CTL2")
    private String courseDate2;

    @ExcelCellName("Mle 2")
    private String instructorNumber2;

    @ExcelCellName("date_CTL3")
    private String courseDate3;

    @ExcelCellName("Mle 3")
    private String instructorNumber3;

    @ExcelCellName("date_CTL4")
    private String courseDate4;

    @ExcelCellName("Mle 4")
    private String instructorNumber4;


    @Override
    public List<Row> getRows() {
        return getRows(4, this.getClass());
    }
}
