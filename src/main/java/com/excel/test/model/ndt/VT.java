package com.excel.test.model.ndt;

import lombok.Getter;

@Getter
public class VT extends NDT {
    public VT(String controlDate, String reportNumber, String result) {
        super(controlDate, reportNumber, result);
    }
}
