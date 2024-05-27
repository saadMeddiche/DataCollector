package org.data.datacollector.dataExtractors.dataHolders.dataFromCAT2;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelSheet("CAT 2,3")
public class CatCAT2 {

    @ExcelCellName("Mle")
    private String employeeNumberOfPnt;

    // CAT II && FINAL
    @ExcelCellName("CAT 2 DTE")
    private String cat2FinalDate;

    @ExcelCellName("Mle2_1")
    private String cat2FinalEmployeeNumberOfInstructor;

    @ExcelCellName("CAT 2 IPL")
    private String cat2FinalInstructorName;

    // CAT II && TRANSITION

    @ExcelCellName("CAT 2 TRS")
    private String cat2TransitionDate;

    @ExcelCellName("Mle2_2")
    private String cat2TransitionEmployeeNumberOfInstructor;

    @ExcelCellName("CAT 2 TRS IPL")
    private String cat2TransitionInstructorName;

    // CAT III && FINAL

    @ExcelCellName("CAT 3 DTE")
    private String cat3FinalDate;

    @ExcelCellName("Mle3_1")
    private String cat3FinalEmployeeNumberOfInstructor;

    @ExcelCellName("CAT 3 IPL")
    private String cat3FinalInstructorName;

    // CAT III && TRANSITION

    @ExcelCellName("CAT 3 TRS")
    private String cat3TransitionDate;

    @ExcelCellName("Mle3_2")
    private String cat3TransitionEmployeeNumberOfInstructor;

    @ExcelCellName("CAT 3 TRS IPL")
    private String cat3TransitionInstructorName;
}
