package com.excel.test.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;

public class ExcelProcessorUtils {

    public static Row getCurrentRowIndex(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        int i = lastRowNum < 0 ? 0 : lastRowNum + 1;
        return sheet.createRow(i);
    }

    public static void uploadValues(Class<?> clazz, Row row, Object instance) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {

            if (field.isAnnotationPresent(ExcelEmbeddedObject.class)) {
                try {
                    field.setAccessible(true);
                    Object embeddedObjectInstance = field.get(instance);
                    Class<?> superclass = embeddedObjectInstance.getClass().getSuperclass();
                    field.setAccessible(false);

                    uploadValues(superclass, row, embeddedObjectInstance);
                    uploadValues(embeddedObjectInstance.getClass(), row, embeddedObjectInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!field.isAnnotationPresent(ExcelIgnore.class)) {
                putFieldValueInExcelRow(field, row, instance);
            }
        }
    }

    private static void putFieldValueInExcelRow(Field field, Row row, Object instance) {
        try {
            field.setAccessible(true);
            String value = (String) field.get(instance);
            Cell cell = getCurrentCellIndex(row);
            cell.setCellValue(value);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Cell getCurrentCellIndex(Row row) {
        if (row.getLastCellNum() < 0) {
            return row.createCell(0);
        }
        return row.createCell(row.getLastCellNum());
    }
}
