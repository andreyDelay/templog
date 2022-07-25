package com.excel.test.excel.basetp;

import com.excel.test.model.TestPack;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface BaseTpReader {

    Map<String, TestPack> generateTestPacksMap(MultipartFile file);
}
