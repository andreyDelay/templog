package com.excel.test.excel.basetp.parser;

import com.excel.test.excel.basetp.BaseTpDetails;
import com.excel.test.model.ndt.NDT;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public interface ExcelEntityFactory {
    NDT createNDT(Row row, BaseTpDetails baseTpDetails);

    default String getTargetCellValue(Row row, int targetCellIndex) {
        try {
            Cell cell = row.getCell(targetCellIndex);
            return cell.getStringCellValue();
        } catch (NullPointerException e) {
            return "";
        }
    }
}
