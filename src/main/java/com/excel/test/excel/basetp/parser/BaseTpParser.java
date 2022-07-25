package com.excel.test.excel.basetp.parser;

import com.excel.test.model.Joint;
import com.excel.test.model.Line;
import com.excel.test.model.TestPack;
import org.apache.poi.ss.usermodel.Row;

import java.util.Map;

public interface BaseTpParser {
    TestPack fetchExistingOrCreateNewTestPack(Row currentRow, Map<String, TestPack> testPacksCollection);
    Line fetchExistingOrCreateNewLine(Row row, TestPack testPack);
    Joint createJoint(Row row);
}
