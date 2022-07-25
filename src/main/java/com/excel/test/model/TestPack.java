package com.excel.test.model;

import com.excel.test.excel.ExcelColumnHeader;
import com.excel.test.excel.ExcelIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Builder
@Document
public class TestPack {
    @Id
    @ExcelIgnore
    private String id;

    @Indexed(unique = true)
    @ExcelColumnHeader(headerValue = "Test pack ID")
    private String testPackNumber;

    @ExcelIgnore
    private Map<String, Line> lines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPack testPack = (TestPack) o;
        return testPackNumber.equals(testPack.testPackNumber);
    }

    @Override
    public int hashCode() {
        return testPackNumber.hashCode();
    }
}
