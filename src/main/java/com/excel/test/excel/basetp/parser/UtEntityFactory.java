package com.excel.test.excel.basetp.parser;

import com.excel.test.excel.basetp.BaseTpDetails;
import com.excel.test.model.ndt.NDT;
import com.excel.test.model.ndt.UT;
import org.apache.poi.ss.usermodel.Row;

public class UtEntityFactory implements ExcelEntityFactory {
    @Override
    public NDT createNDT(Row row, BaseTpDetails baseTpDetails) {
        var date = getTargetCellValue(row, baseTpDetails.getUt_controlDate_columnIdx() - 1);
        var report = getTargetCellValue(row, baseTpDetails.getUt_reportNumber_columnIdx() - 1);
        var result = getTargetCellValue(row, baseTpDetails.getUt_result_columnIdx() - 1);
        return new UT(date, report, result);
    }
}
