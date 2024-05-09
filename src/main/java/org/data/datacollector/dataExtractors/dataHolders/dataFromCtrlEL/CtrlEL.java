package org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL;

import com.poiji.annotation.ExcelCellName;
import lombok.*;
import org.data.datacollector.services.ClassHelper;

import java.util.List;
import java.util.stream.IntStream;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CtrlEL {

    @ExcelCellName("MATR")
    private String employeeNumber;

    @ExcelCellName("Dte CTL1")
    private String startDate1;

    @ExcelCellName("Butée EL1")
    private String buteeDate1;

    @ExcelCellName("Mle1")
    private String instructorNumber1;

    @ExcelCellName("Dte CTL2")
    private String startDate2;

    @ExcelCellName("Butée EL2")
    private String buteeDate2;

    @ExcelCellName("Mle2")
    private String instructorNumber2;

    @ExcelCellName("Dte CTL3")
    private String startDate3;

    @ExcelCellName("Butée EL3")
    private String buteeDate3;

    @ExcelCellName("Mle3")
    private String instructorNumber3;

    @ExcelCellName("Dte CTL4")
    private String startDate4;

    @ExcelCellName("Butée EL4")
    private String buteeDate4;

    @ExcelCellName("Mle4")
    private String instructorNumber4;

    public List<Row> getRows(){

        ClassHelper ch = new ClassHelper(this.getClass() , this);

        return IntStream.range(1, 5)
                .mapToObj(i -> Row.builder()
                        .startDate(ch.getValueFieldByName("startDate" + i))
                        .buteeDate(ch.getValueFieldByName("buteeDate" + i))
                        .instructorNumber(ch.getValueFieldByName("instructorNumber" + i))
                        .build())
                .toList();
    }

    @Builder
    @Getter
    public static class Row {
        private String startDate;
        private String buteeDate;
        private String instructorNumber;
    }
}
