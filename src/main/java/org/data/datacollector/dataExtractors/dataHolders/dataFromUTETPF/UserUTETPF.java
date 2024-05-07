package org.data.datacollector.dataExtractors.dataHolders.dataFromUTETPF;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUTETPF {
    @ExcelCellName("employee_number")
    private String employeeNumber;

    @ExcelCellName("first_name")
    private String firstName;

    @ExcelCellName("last_name")
    private String lastName;
}
