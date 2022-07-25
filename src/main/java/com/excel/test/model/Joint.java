package com.excel.test.model;

import com.excel.test.excel.ExcelColumnHeader;
import com.excel.test.excel.ExcelEmbeddedObject;
import com.excel.test.excel.ExcelIgnore;
import com.excel.test.model.ndt.*;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class Joint {
    @ExcelIgnore
    private String ISO;
    @ExcelColumnHeader(headerValue = "Material")
    private String material;
    @ExcelColumnHeader(headerValue = "Weld location")
    private String location;
    @ExcelColumnHeader(headerValue = "Weld joint number")
    private String jointNumber;
    @ExcelColumnHeader(headerValue = "Repair")
    private String repairTag;
    @ExcelColumnHeader(headerValue = "Weld joint type")
    private String jointType;
    @ExcelColumnHeader(headerValue = "Inch size")
    private String inches;
    @ExcelColumnHeader(headerValue = "Welding method")
    private String weldingMethod;
    @ExcelColumnHeader(headerValue = "Date of weld")
    private String weldingDate;
    @ExcelColumnHeader(headerValue = "Root pass welder 1")
    private String welderRoot1;
    @ExcelColumnHeader(headerValue = "Root pass welder 2")
    private String welderRoot2;
    @ExcelColumnHeader(headerValue = "Fill and face passes welder 1")
    private String welderFace1;
    @ExcelColumnHeader(headerValue = "Fill and face passes welder 2")
    private String welderFace2;

    @ExcelEmbeddedObject
    private VT vt;
    @ExcelEmbeddedObject
    private PT pt;
    @ExcelEmbeddedObject
    private UT ut;
    @ExcelEmbeddedObject
    private RT rt;
    @ExcelEmbeddedObject
    private PMI pmi;
    @ExcelEmbeddedObject
    private PWHT pwht;

    @ExcelEmbeddedObject
    private NCR ncr;
    @ExcelEmbeddedObject
    private RepairStatus repairStatus;

    @ExcelColumnHeader(headerValue = "Item 1 inch size")
    private String item1_inchSize;
    @ExcelColumnHeader(headerValue = "Item 1 diameter")
    private String item1_diameter;
    @ExcelColumnHeader(headerValue = "Item 1 thickness code")
    private String item1_thicknessCode;
    @ExcelColumnHeader(headerValue = "Item 1 thickness")
    private String item1_thickness;

    @ExcelColumnHeader(headerValue = "Item 2 inch size")
    private String item2_inchSize;
    @ExcelColumnHeader(headerValue = "Item 2 diameter")
    private String item2_diameter;
    @ExcelColumnHeader(headerValue = "Item 2 thickness code")
    private String item2_thicknessCode;
    @ExcelColumnHeader(headerValue = "Item 2 thickness")
    private String item2_thickness;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Joint joint = (Joint) o;

        if (!ISO.equals(joint.ISO)) return false;
        if (!location.equals(joint.location)) return false;
        if (!jointNumber.equals(joint.jointNumber)) return false;
        return repairTag.equals(joint.repairTag);
    }

    @Override
    public int hashCode() {
        int result = ISO.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + jointNumber.hashCode();
        result = 31 * result + repairTag.hashCode();
        return result;
    }
}
