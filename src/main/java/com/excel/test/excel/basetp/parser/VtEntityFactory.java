package com.excel.test.excel.basetp.parser;

import com.excel.test.excel.basetp.BaseTpDetails;
import com.excel.test.model.ndt.NDT;
import com.excel.test.model.ndt.VT;
import org.apache.poi.ss.usermodel.Row;

public class VtEntityFactory implements ExcelEntityFactory {
    @Override
    public NDT createNDT(Row row, BaseTpDetails baseTpDetails) {
        var date = getTargetCellValue(row, baseTpDetails.getVt_controlDate_columnIdx() - 1);
        var report = getTargetCellValue(row, baseTpDetails.getVt_reportNumber_columnIdx() - 1);
        var result = getTargetCellValue(row, baseTpDetails.getVt_result_columnIdx() - 1);
        return new VT(date, report, result);
    }
}
