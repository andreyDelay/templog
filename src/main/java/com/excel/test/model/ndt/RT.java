package com.excel.test.model.ndt;

import lombok.Getter;

@Getter
public class RT extends NDT {
    public RT(String controlDate, String reportNumber, String result) {
        super(controlDate, reportNumber, result);
    }
}
