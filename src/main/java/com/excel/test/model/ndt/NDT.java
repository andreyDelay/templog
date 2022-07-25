package com.excel.test.model.ndt;

import com.excel.test.excel.ExcelColumnHeader;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NDT {
    @ExcelColumnHeader(headerValue = "control date")
    private String controlDate;
    @ExcelColumnHeader(headerValue = "report number")
    private String reportNumber;
    @ExcelColumnHeader(headerValue = "result")
    private String result;
}
