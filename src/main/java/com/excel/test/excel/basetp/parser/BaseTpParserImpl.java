package com.excel.test.excel.basetp.parser;

import com.excel.test.excel.basetp.BaseTpDetails;
import com.excel.test.model.*;
import com.excel.test.model.ndt.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BaseTpParserImpl implements BaseTpParser {

    private final BaseTpDetails baseTpDetails;

    @Override
    public TestPack fetchExistingOrCreateNewTestPack(Row currentRow, Map<String, TestPack> testPacksCollection) {
        int testPackNumber_columnIdx = (baseTpDetails.getTestPackNumber_columnIdx() - 1);
        var testPackNumber = fetchTargetStructureNumberFromWorksheet(currentRow, testPackNumber_columnIdx);
        TestPack existingTestPack = testPacksCollection.get(testPackNumber);

        if (existingTestPack != null) {
            return existingTestPack;
        }

        TestPack newTestPack = TestPack.builder()
                .testPackNumber(testPackNumber)
                .lines(new HashMap<>())
                .build();
        testPacksCollection.put(testPackNumber, newTestPack);
        return newTestPack;
    }

    @Override
    public Line fetchExistingOrCreateNewLine(Row row, TestPack testPack) {
        int lineNumber_columnIdx = (baseTpDetails.getLineNumber_columnIdx() - 1);
        String existingLineNumber = fetchTargetStructureNumberFromWorksheet(row, lineNumber_columnIdx);

        Map<String, Line> lines = testPack.getLines();
        Line existingLine = lines.get(existingLineNumber);

        if (existingLine != null) {
            return existingLine;
        }

        var lineNumber = getCellValue(row,baseTpDetails.getLineNumber_columnIdx() - 1);
        var area = getCellValue(row,baseTpDetails.getArea_columnIdx()- 1);
        var iso = getCellValue(row,baseTpDetails.getISO_columnIdx() - 1);
        var category = getCellValue(row,baseTpDetails.getCategory_columnIdx() - 1);
        var percent = getCellValue(row,baseTpDetails.getPercent_columnIdx() - 1);
        var revision = getCellValue(row,baseTpDetails.getRevision_columnIdx() - 1);
        var spoolNumber = getCellValue(row, baseTpDetails.getSpoolNumber_columnIdx() - 1);

        Line line = Line.builder()
                .area(area)
                .lineNumber(lineNumber)
                .ISO(iso)
                .category(category)
                .percent(percent)
                .revision(revision)
                .spoolNumber(spoolNumber)
                .joints(new HashSet<>())
                .build();

        testPack.getLines().put(lineNumber, line);
        return line;
    }

    private String fetchTargetStructureNumberFromWorksheet(Row row, int targetCellIndex) {
        try {
            String stringCellValue = row.getCell(targetCellIndex).getStringCellValue();
            return stringCellValue.replaceAll(" ", "");
        } catch (NullPointerException e) {
            return "unknown in row:" + row.getRowNum();
        }
    }

    @Override
    public Joint createJoint(Row row) {
        VT vt = fetchVT(row);
        PT pt = fetchPT(row);
        UT ut = fetchUT(row);
        RT rt = fetchRT(row);
        PMI pmi = fetchPMI(row);
        PWHT pwht = fetchPWHT(row);

        NCR ncr = fetchNcr(row);
        RepairStatus repairStatus = fetchRepairStatus(row);

        var iso = getCellValue(row, baseTpDetails.getISO_columnIdx() - 1);
        var material = getCellValue(row, baseTpDetails.getMaterial_columnIdx() - 1);
        var location = getCellValue(row, baseTpDetails.getLocation_columnIdx() - 1);
        var jointNumber = getCellValue(row, baseTpDetails.getJointNumber_columnIdx() - 1);
        var repairTag = getCellValue(row, baseTpDetails.getRepairTag_columnIdx() - 1);
        var jointType = getCellValue(row, baseTpDetails.getJointType_columnIdx() - 1);
        var inches = getCellValue(row, baseTpDetails.getInches_columnIdx() - 1);
        var weldingMethod = getCellValue(row, baseTpDetails.getWeldingMethod_columnIdx() -1);
        var weldingDate = getCellValue(row, baseTpDetails.getWeldingDate_columnIdx() - 1);
        var welderRoot1 = getCellValue(row, baseTpDetails.getWelderRoot1_columnIdx() - 1);
        var welderRoot2 = getCellValue(row, baseTpDetails.getWelderRoot2_columnIdx() - 1);
        var welderFace1 = getCellValue(row, baseTpDetails.getWelderFace1_columnIdx() -1);
        var welderFace2 = getCellValue(row, baseTpDetails.getWelderFace2_columnIdx() -1);

        var item1_inchSize = getCellValue(row, baseTpDetails.getItem1_inchSize_columnIdx() - 1);
        var item1_diameter = getCellValue(row, baseTpDetails.getItem1_diameter_columnIdx() - 1);
        var item1_thicknessCode = getCellValue(row, baseTpDetails.getItem1_thicknessCode_columnIdx() - 1);
        var item1_thickness = getCellValue(row, baseTpDetails.getItem1_thickness_columnIdx() - 1);

        var item2_inchSize = getCellValue(row, baseTpDetails.getItem2_inchSize_columnIdx() - 1);
        var item2_diameter = getCellValue(row, baseTpDetails.getItem2_diameter_columnIdx() - 1);
        var item2_thicknessCode = getCellValue(row, baseTpDetails.getItem2_thicknessCode_columnIdx() - 1);
        var item2_thickness = getCellValue(row, baseTpDetails.getItem2_thickness_columnIdx() - 1);

        return Joint.builder()
                .vt(vt).pt(pt).ut(ut).rt(rt).pmi(pmi).pwht(pwht)
                .ncr(ncr).repairStatus(repairStatus)
                .ISO(iso)
                .material(material)
                .location(location)
                .jointNumber(jointNumber)
                .repairTag(repairTag)
                .jointType(jointType)
                .inches(inches)
                .weldingMethod(weldingMethod)
                .weldingDate(weldingDate)
                .welderRoot1(welderRoot1)
                .welderRoot2(welderRoot2)
                .welderFace1(welderFace1)
                .welderFace2(welderFace2)
                .item1_inchSize(item1_inchSize)
                .item1_diameter(item1_diameter)
                .item1_thicknessCode(item1_thicknessCode)
                .item1_thickness(item1_thickness)
                .item2_inchSize(item2_inchSize)
                .item2_diameter(item2_diameter)
                .item2_thicknessCode(item2_thicknessCode)
                .item2_thickness(item2_thickness)
                .build();
    }

    private VT fetchVT(Row row) {
        ExcelEntityFactory factory = new VtEntityFactory();
        return (VT)factory.createNDT(row, baseTpDetails);
    }

    private PT fetchPT(Row row) {
        ExcelEntityFactory factory = new VtEntityFactory();
        return (PT)factory.createNDT(row, baseTpDetails);
    }

    private UT fetchUT(Row row) {
        ExcelEntityFactory factory = new VtEntityFactory();
        return (UT)factory.createNDT(row, baseTpDetails);
    }

    private RT fetchRT(Row row) {
        ExcelEntityFactory factory = new VtEntityFactory();
        return (RT)factory.createNDT(row, baseTpDetails);
    }

    private PMI fetchPMI(Row row) {
        ExcelEntityFactory factory = new VtEntityFactory();
        return (PMI)factory.createNDT(row, baseTpDetails);
    }

    private NCR fetchNcr(Row row) {
        var ncrDate = getCellValue(row, baseTpDetails.getNcrDate_columnIdx() - 1);
        var ncrNumber = getCellValue(row, baseTpDetails.getNcrNumber_columnIdx() - 1);
        var ncrStatus = getCellValue(row, baseTpDetails.getNcrStatus_columnIdx() - 1);

        return NCR.builder()
                .ncrDate(ncrDate)
                .ncrNumber(ncrNumber)
                .ncrStatus(ncrStatus)
                .build();
    }

    private PWHT fetchPWHT(Row row) {
        var controlDate = getCellValue(row, baseTpDetails.getPwhtDate_columnIdx() - 1);
        var diagramNumber = getCellValue(row, baseTpDetails.getPwhtDiagramNumber_columnIdx() - 1);
        var hardnessTestReport = getCellValue(row, baseTpDetails.getPwhtHardnessTestReportNumber_columnIdx() - 1);
        var operatorStamp = getCellValue(row, baseTpDetails.getPwhtOperatorStamp_columnIdx() - 1);
        var result = getCellValue(row, baseTpDetails.getPwhtResult_columnIdx() - 1);

        return PWHT.builder()
                .controlDate(controlDate)
                .diagramNumber(diagramNumber)
                .hardnessTestReportNumber(hardnessTestReport)
                .operatorStamp(operatorStamp)
                .result(result)
                .build();
    }

    private RepairStatus fetchRepairStatus(Row row) {
        var repairDate = getCellValue(row, baseTpDetails.getRepairDate_columnIdx() - 1);
        var reportNumber = getCellValue(row, baseTpDetails.getRepairReportNumber_columnIdx() - 1);
        var CR = getCellValue(row, baseTpDetails.getRepairCR_columnIdx() - 1);
        var latestRepairDate = getCellValue(row, baseTpDetails.getLatestRepairDate_columnIdx() - 1);
        var request = getCellValue(row, baseTpDetails.getRepairRequest_columnIdx() - 1);

        return RepairStatus.builder()
                .repairDate(repairDate)
                .reportNumber(reportNumber)
                .CR(CR)
                .latestRepairDate(latestRepairDate)
                .request(request)
                .build();
    }

    private String getCellValue(Row row, int targetCellIndex) {
        try {
            Cell cell = row.getCell(targetCellIndex);
            return cell.getStringCellValue();
        } catch (NullPointerException e) {
            return "";
        }
    }
}
