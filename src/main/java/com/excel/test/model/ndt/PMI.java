package com.excel.test.model.ndt;

import lombok.Getter;

@Getter
public class PMI extends NDT {
    public PMI(String controlDate, String reportNumber, String result) {
        super(controlDate, reportNumber, result);
    }
}
