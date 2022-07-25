package com.excel.test.model.ndt;

import lombok.Getter;

@Getter
public class PT extends NDT {
    public PT(String controlDate, String reportNumber, String result) {
        super(controlDate, reportNumber, result);
    }
}
