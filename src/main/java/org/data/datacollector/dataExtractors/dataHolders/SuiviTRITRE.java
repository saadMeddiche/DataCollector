package org.data.datacollector.dataExtractors.dataHolders;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuiviTRITRE {

    @ExcelCellName("Mle")
    private String employeeNumber;

    @ExcelCellName("nominationStatus")
    private String nominationStatus;

    @ExcelCellName("nominationDate")
    private String nominationDate;

    @ExcelCellName("sieDu")
    private String sieDu;

    @ExcelCellName("sieAu")
    private String sieAu;

    @ExcelCellName("N° Aut TRI")
    private String authorisationNumberTRI;

    @ExcelCellName("DuTRI")
    private String startDateTRI;

    @ExcelCellName("AuTRI")
    private String endDateTRI;

    @ExcelCellName("N° Aut TRE")
    private String authorisationNumberTRE;

    @ExcelCellName("DuTRE")
    private String startDateTRE;

    @ExcelCellName("AuTRE")
    private String endDateTRE;

    @ExcelCellName("DuFI_ATPL")
    private String startDateFIATPL;

    @ExcelCellName("AuFI_ATPL")
    private String endDateFIATPL;

    @ExcelCellName("DteHabilitation")
    private String dateHabilitation;

    @ExcelCellName("SIEHabilitation")
    private String sieHabilitation;

    @ExcelCellName("DteSimuPlaceDroite")
    private String dateSimuPlaceDroite;

    @ExcelCellName("TRESimuPlaceDroite")
    private String treSimuPlaceDroite;

    @ExcelCellName("DteDG")
    private String dateDG;

    @ExcelCellName("SIEDG")
    private String sieDG;
}
