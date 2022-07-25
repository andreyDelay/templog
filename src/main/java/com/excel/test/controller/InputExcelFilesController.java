package com.excel.test.controller;

import com.excel.test.dto.BaseTpUpdateDto;
import com.excel.test.dto.ConstructionRepairsUpdateDto;
import com.excel.test.dto.WeldersAdmissionsUpdateDto;
import com.excel.test.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/excel/upload")
public class InputExcelFilesController {

    private final ExcelService excelService;

    @PostMapping("/basetp")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseTpUpdateDto uploadBaseTp(@RequestParam MultipartFile baseTpExcelFile) {
        return excelService.updateBaseTpInfo(baseTpExcelFile);
    }

    @PostMapping("/repairs")
    @ResponseStatus(HttpStatus.CREATED)
    public ConstructionRepairsUpdateDto uploadConstructionRepairs(@RequestParam MultipartFile constructionTable) {
        return null;
    }

    @PostMapping("/welders/admissions")
    @ResponseStatus(HttpStatus.CREATED)
    public WeldersAdmissionsUpdateDto uploadWeldersAdmissions(@RequestParam MultipartFile baseTpExcelFile) {
        return null;
    }
}
