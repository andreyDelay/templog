package com.excel.test.excel.basetp.parser;

import com.excel.test.excel.basetp.BaseTpDetails;
import com.excel.test.model.ndt.NDT;
import com.excel.test.model.ndt.PMI;
import org.apache.poi.ss.usermodel.Row;

public class PmiEntityFactory implements ExcelEntityFactory {
    @Override
    public NDT createNDT(Row row, BaseTpDetails baseTpDetails) {
        var date = getTargetCellValue(row, baseTpDetails.getPmi_controlDate_columnIdx() - 1);
        var report = getTargetCellValue(row, baseTpDetails.getPmi_reportNumber_columnIdx() - 1);
        var result = getTargetCellValue(row, baseTpDetails.getPmi_result_columnIdx() - 1);
        return new PMI(date, report, result);
    }
}
