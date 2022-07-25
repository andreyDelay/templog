package com.excel.test.model.ndt;

import lombok.Getter;

@Getter
public class UT extends NDT {
    public UT(String controlDate, String reportNumber, String result) {
        super(controlDate, reportNumber, result);
    }
}
