package com.excel.test.model;

import com.excel.test.excel.ExcelColumnHeader;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PWHT {
    @ExcelColumnHeader(headerValue = "Date of PWHT")
    private String controlDate;
    @ExcelColumnHeader(headerValue = "Operator stamp")
    private String operatorStamp;
    @ExcelColumnHeader(headerValue = "PWHT diagram")
    private String diagramNumber;
    @ExcelColumnHeader(headerValue = "Hardness test report №")
    private String hardnessTestReportNumber;
    @ExcelColumnHeader(headerValue = "PWHT result")
    private String result;
}
