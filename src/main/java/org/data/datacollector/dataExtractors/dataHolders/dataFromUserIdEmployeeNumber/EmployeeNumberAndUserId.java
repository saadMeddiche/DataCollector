package org.data.datacollector.dataExtractors.dataHolders.dataFromUserIdEmployeeNumber;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeNumberAndUserId {

    @ExcelCellName("employee_number")
    private String employeeNumber;

    @ExcelCellName("id")
    private String userId;
}
