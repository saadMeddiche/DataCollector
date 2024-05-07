package org.data.datacollector.dataExtractors.dataHolders.dataFromSimu;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.data.datacollector.dataExtractors.global.CourseData;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSimu extends CourseData {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("date1")
    private String courseDate1;

    @ExcelCellName("Mle1")
    private String instructorNumber1;

    @ExcelCellName("cat_II_1")
    private String catTwo1;

    @ExcelCellName("cat_III_1")
    private String catThree1;

    @ExcelCellName("date2")
    private String courseDate2;

    @ExcelCellName("Mle2")
    private String instructorNumber2;

    @ExcelCellName("cat_II_2")
    private String catTwo2;

    @ExcelCellName("cat_III_2")
    private String catThree2;

    @ExcelCellName("date3")
    private String courseDate3;

    @ExcelCellName("Mle3")
    private String instructorNumber3;

    @ExcelCellName("cat_II_3")
    private String catTwo3;

    @ExcelCellName("cat_III_3")
    private String catThree3;

    @ExcelCellName("date4")
    private String courseDate4;

    @ExcelCellName("Mle4")
    private String instructorNumber4;

    @ExcelCellName("cat_II_4")
    private String catTwo4;

    @ExcelCellName("cat_III_4")
    private String catThree4;

    @ExcelCellName("date5")
    private String courseDate5;

    @ExcelCellName("Mle5")
    private String instructorNumber5;

    @ExcelCellName("cat_II_5")
    private String catTwo5;

    @ExcelCellName("cat_III_5")
    private String catThree5;

    @ExcelCellName("date6")
    private String courseDate6;

    @ExcelCellName("Mle6")
    private String instructorNumber6;

    @ExcelCellName("cat_II_6")
    private String catTwo6;

    @ExcelCellName("cat_III_6")
    private String catThree6;

    @ExcelCellName("date7")
    private String courseDate7;

    @ExcelCellName("Mle7")
    private String instructorNumber7;

    @ExcelCellName("cat_II_7")
    private String catTwo7;

    @ExcelCellName("cat_III_7")
    private String catThree7;

    @ExcelCellName("date8")
    private String courseDate8;

    @ExcelCellName("Mle8")
    private String instructorNumber8;

    @ExcelCellName("cat_II_8")
    private String catTwo8;

    @ExcelCellName("cat_III_8")
    private String catThree8;

    @Override
    public List<Row> getRows() {
        return getRows(8, CourseSimu.class);
    }
}
