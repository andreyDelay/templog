package com.excel.test.excel.basetp.parser;

import com.excel.test.excel.basetp.BaseTpDetails;
import com.excel.test.model.ndt.NDT;
import com.excel.test.model.ndt.PT;
import org.apache.poi.ss.usermodel.Row;

public class PtEntityFactory implements ExcelEntityFactory {
    @Override
    public NDT createNDT(Row row, BaseTpDetails baseTpDetails) {
        var date = getTargetCellValue(row, baseTpDetails.getPt_controlDate_columnIdx() - 1);
        var report = getTargetCellValue(row, baseTpDetails.getPt_reportNumber_columnIdx() - 1);
        var result = getTargetCellValue(row, baseTpDetails.getPt_result_columnIdx() - 1);
        return new PT(date, report, result);
    }
}
