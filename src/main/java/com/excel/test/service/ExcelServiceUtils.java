package com.excel.test.service;

import com.excel.test.model.TestPack;

import java.util.List;
import java.util.Map;

public class ExcelServiceUtils {

    public static int getProcessedTestPacksQty(List<TestPack> testPackList) {
        return testPackList.size();
    }

    public static int getProcessedLinesQty(List<TestPack> testPackList) {
        return testPackList.stream()
                .map(value -> value.getLines().size())
                .reduce(0, Integer::sum);
    }

    public static int getProcessedJointsQty(List<TestPack> testPackList) {
        return testPackList.stream()
                .map(TestPack::getLines)
                .map(Map::values)
                .map(lines -> lines.stream()
                        .map(line -> line.getJoints().size())
                        .reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }
}
