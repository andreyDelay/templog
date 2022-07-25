package com.excel.test.excel.basetp;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BaseTpDetails {

    private String targetWorksheetName = "LOG";
    private int firstRow = 2;

    //TP
    private int testPackNumber_columnIdx = 53;
    //Line
    private int area_columnIdx = 1;
    private int lineNumber_columnIdx = 2;
    private int ISO_columnIdx = 5;
    private int category_columnIdx = 7;
    private int percent_columnIdx = 8;
    private int revision_columnIdx = 9;

    //Joint
    private int material_columnIdx = 11;
    private int spoolNumber_columnIdx = 12;
    private int location_columnIdx = 13;
    private int jointNumber_columnIdx = 14;
    private int repairTag_columnIdx = 15;
    private int jointType_columnIdx = 16;
    private int inches_columnIdx = 17;
    private int weldingMethod_columnIdx = 18;
    private int weldingDate_columnIdx = 20;
    private int welderRoot1_columnIdx = 21;
    private int welderRoot2_columnIdx = 22;
    private int welderFace1_columnIdx = 23;
    private int welderFace2_columnIdx = 24;
    private int item1_inchSize_columnIdx = 69;
    private int item1_diameter_columnIdx = 70;
    private int item1_thicknessCode_columnIdx = 71;
    private int item1_thickness_columnIdx = 72;
    private int item2_inchSize_columnIdx = 73;
    private int item2_diameter_columnIdx = 74;
    private int item2_thicknessCode_columnIdx = 75;
    private int item2_thickness_columnIdx = 76;

    //repair
    private int repairDate_columnIdx = 40;
    private int repairReportNumber_columnIdx = 41;
    private int repairCR_columnIdx = 42;
    private int latestRepairDate_columnIdx = 43;
    private int repairRequest_columnIdx = 44;

    //NCR
    private int ncrNumber_columnIdx = 63;
    private int ncrDate_columnIdx = 64;
    private int ncrStatus_columnIdx = 65;

    //PWHT
    private int pwhtDate_columnIdx = 48;
    private int pwhtOperatorStamp_columnIdx = 49;
    private int pwhtDiagramNumber_columnIdx = 50;
    private int pwhtHardnessTestReportNumber_columnIdx = 51;
    private int pwhtResult_columnIdx = 52;

    //NDT
    private int vt_controlDate_columnIdx = 28;
    private int vt_reportNumber_columnIdx = 29;
    private int vt_result_columnIdx = 30;

    private int pt_controlDate_columnIdx = 31;
    private int pt_reportNumber_columnIdx = 32;
    private int pt_result_columnIdx = 33;

    private int ut_controlDate_columnIdx = 34;
    private int ut_reportNumber_columnIdx = 35;
    private int ut_result_columnIdx = 36;

    private int rt_controlDate_columnIdx = 37;
    private int rt_reportNumber_columnIdx = 38;
    private int rt_result_columnIdx = 39;

    private int pmi_controlDate_columnIdx = 45;
    private int pmi_reportNumber_columnIdx = 46;
    private int pmi_result_columnIdx = 47;

}
