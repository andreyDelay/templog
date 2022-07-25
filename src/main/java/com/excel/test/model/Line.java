package com.excel.test.model;

import com.excel.test.excel.ExcelColumnHeader;
import com.excel.test.excel.ExcelIgnore;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class Line {
    @ExcelColumnHeader(headerValue = "Line number")
    private String lineNumber;
    @ExcelColumnHeader(headerValue = "area")
    private String area;
    @ExcelColumnHeader(headerValue = "ISO number")
    private String ISO;
    @ExcelColumnHeader(headerValue = "Pipe category")
    private String category;
    @ExcelColumnHeader(headerValue = "NDT percent")
    private String percent;
    @ExcelColumnHeader(headerValue = "Line revision")
    private String revision;

    @ExcelColumnHeader(headerValue = "Spool number")
    private String spoolNumber;

    @ExcelIgnore
    private Set<Joint> joints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!lineNumber.equals(line.lineNumber)) return false;
        if (!area.equals(line.area)) return false;
        return ISO.equals(line.ISO);
    }

    @Override
    public int hashCode() {
        int result = lineNumber.hashCode();
        result = 31 * result + area.hashCode();
        result = 31 * result + ISO.hashCode();
        return result;
    }
}
