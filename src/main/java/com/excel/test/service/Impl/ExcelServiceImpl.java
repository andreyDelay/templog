package com.excel.test.service.Impl;

import com.excel.test.dto.BaseTpUpdateDto;
import com.excel.test.excel.basetp.BaseTpReader;
import com.excel.test.excel.basetp.BaseTpWriter;
import com.excel.test.model.TestPack;
import com.excel.test.repository.TestPacksRepository;
import com.excel.test.service.ExcelService;
import com.excel.test.service.ExcelServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    private final BaseTpReader baseTpReader;
    private final TestPacksRepository testPacksRepository;
    private final BaseTpWriter baseTpWriter;

    @Override
    public BaseTpUpdateDto updateBaseTpInfo(MultipartFile file) {
        List<TestPack> testPackList = baseTpReader.generateTestPacksMap(file).values().stream().toList();
        int testPacksQty = ExcelServiceUtils.getProcessedTestPacksQty(testPackList);
        int linesQty =  ExcelServiceUtils.getProcessedLinesQty(testPackList);
        int jointsQty = ExcelServiceUtils.getProcessedJointsQty(testPackList);

        testPacksRepository.saveAll(testPackList);
        return BaseTpUpdateDto.builder()
                .testPacksQty(testPacksQty)
                .linesQty(linesQty)
                .jointsQty(jointsQty)
                .build();
    }

    @Override
    public File getBaseTpExcelFile(String filename) {
        List<TestPack> all = testPacksRepository.findAll();
        return baseTpWriter.generateBaseTpExcelFile(filename, all).orElseThrow(RuntimeException::new);
        //TODO custom exception
    }


}
