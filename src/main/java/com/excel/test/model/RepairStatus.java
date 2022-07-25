package com.excel.test.model;

import com.excel.test.excel.ExcelColumnHeader;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RepairStatus {
    @ExcelColumnHeader(headerValue = "Repair date")
    private String repairDate;
    @ExcelColumnHeader(headerValue = "NDT report number")
    private String reportNumber;
    @ExcelColumnHeader(headerValue = "C/R")
    private String CR;
    @ExcelColumnHeader(headerValue = "Latest repair finished date")
    private String latestRepairDate;
    @ExcelColumnHeader(headerValue = "Request")
    private String request;
}
