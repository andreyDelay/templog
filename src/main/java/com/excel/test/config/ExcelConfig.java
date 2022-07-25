package com.excel.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "excel.file")
public class ExcelConfig {
    private String fileFormat;
    private String mainWorksheetName;
    private String additionalWorksheetName;
    private int headerRow;
}
