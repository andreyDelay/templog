package com.excel.test.excel.basetp;

import com.excel.test.model.TestPack;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface BaseTpWriter {

    Optional<File> generateBaseTpExcelFile(String filename, List<TestPack> testPackList);
}
