package org.data.datacollector.dataExtractor.dataHolders.dataFromUserIdEmployeeNumber;

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
    private Long employeeNumber;

    @ExcelCellName("id")
    private Long userId;
}
