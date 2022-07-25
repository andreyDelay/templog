package com.excel.test.excel.basetp.parser;

import com.excel.test.excel.basetp.BaseTpDetails;
import com.excel.test.model.ndt.NDT;
import com.excel.test.model.ndt.RT;
import org.apache.poi.ss.usermodel.Row;

public class RtEntityFactory implements ExcelEntityFactory {
    @Override
    public NDT createNDT(Row row, BaseTpDetails baseTpDetails) {
        var date = getTargetCellValue(row, baseTpDetails.getRt_controlDate_columnIdx() - 1);
        var report = getTargetCellValue(row, baseTpDetails.getRt_reportNumber_columnIdx() - 1);
        var result = getTargetCellValue(row, baseTpDetails.getRt_result_columnIdx() - 1);
        return new RT(date, report, result);
    }
}
