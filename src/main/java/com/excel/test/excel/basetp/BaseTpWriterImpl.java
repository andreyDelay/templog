package com.excel.test.excel.basetp;

import com.excel.test.config.ExcelConfig;
import com.excel.test.excel.ExcelProcessorUtils;
import com.excel.test.model.Joint;
import com.excel.test.model.Line;
import com.excel.test.model.TestPack;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BaseTpWriterImpl implements BaseTpWriter {

    private final ExcelConfig excelConfig;

    @Override
    public Optional<File> generateBaseTpExcelFile(String filename, List<TestPack> testPackList) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(excelConfig.getMainWorksheetName());
            for (TestPack testPack : testPackList) {
                for (Line line : testPack.getLines().values()) {
                    for (Joint joint : line.getJoints()) {
                        Row row = ExcelProcessorUtils.getCurrentRowIndex(sheet);
                        ExcelProcessorUtils.uploadValues(testPack.getClass(), row, testPack);
                        ExcelProcessorUtils.uploadValues(line.getClass(), row, line);
                        ExcelProcessorUtils.uploadValues(joint.getClass(), row, joint);
                    }
                }
            }
            File file = new File(filename + excelConfig.getFileFormat());
            workbook.write(new FileOutputStream(file));
            return Optional.of(file);
        } catch (IOException e) {
            //TODO log error
            return Optional.empty();
        }
    }
}
