package com.excel.test.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BaseTpUpdateDto {
    private int testPacksQty;
    private int linesQty;
    private int jointsQty;
}
