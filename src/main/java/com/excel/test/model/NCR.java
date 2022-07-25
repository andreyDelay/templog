package com.excel.test.model;

import com.excel.test.excel.ExcelColumnHeader;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NCR {
    @ExcelColumnHeader(headerValue = "NCR number")
    private String ncrNumber;
    @ExcelColumnHeader(headerValue = "NCR open date")
    private String ncrDate;
    @ExcelColumnHeader(headerValue = "NCR status")
    private String ncrStatus;
}
