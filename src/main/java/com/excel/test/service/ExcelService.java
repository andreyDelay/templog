package com.excel.test.service;

import com.excel.test.dto.BaseTpUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ExcelService {
    BaseTpUpdateDto updateBaseTpInfo(MultipartFile file);

    File getBaseTpExcelFile(String filename);
}
