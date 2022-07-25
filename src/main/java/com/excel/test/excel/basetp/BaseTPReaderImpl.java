package com.excel.test.excel.basetp;

import com.excel.test.excel.basetp.parser.BaseTpParserImpl;
import com.excel.test.model.Line;
import com.excel.test.model.TestPack;
import com.monitorjbl.xlsx.StreamingReader;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BaseTPReaderImpl implements BaseTpReader {

    private final BaseTpParserImpl baseTpService;
    private final BaseTpDetails baseTpDetails;

    @Override
    public Map<String, TestPack> generateTestPacksMap(MultipartFile file) {
        Map<String, TestPack> testPacksCollection = new LinkedHashMap<>();
        try (InputStream is = file.getInputStream();
             Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)
                .bufferSize(4096)
                .open(is)) {

            for (Sheet sheet : workbook) {
                if (sheet.getSheetName().equals(baseTpDetails.getTargetWorksheetName())) {
                    for (Row row : sheet) {
                        if (row.getRowNum() >= baseTpDetails.getFirstRow()) {
                            if (mandatoryCellsHaveValue(row)) {
                                TestPack testPack = baseTpService.fetchExistingOrCreateNewTestPack(row, testPacksCollection);
                                Line line = baseTpService.fetchExistingOrCreateNewLine(row, testPack);
                                line.getJoints().add(baseTpService.createJoint(row));
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return testPacksCollection;
    }

    private boolean mandatoryCellsHaveValue(Row row) {
        Cell testPackCell = row.getCell(baseTpDetails.getTestPackNumber_columnIdx() - 1);
        Cell lineCell = row.getCell(baseTpDetails.getLineNumber_columnIdx() - 1);
        return
                (testPackCell != null && lineCell != null) &&
                        (testPackCell.getStringCellValue().length() > 0 && lineCell.getStringCellValue().length() > 0);
    }
}
