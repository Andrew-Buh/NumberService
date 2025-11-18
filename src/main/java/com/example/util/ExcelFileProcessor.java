package com.example.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ExcelFileProcessor {

    public int[] readNumbersFromFile(String filePath) {
        log.debug("Чтение чисел из файла: {}", filePath);

        List<Integer> numbers = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = 0;
            int numberCount = 0;

            for (Row row : sheet) {
                rowCount++;
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    int number = (int) cell.getNumericCellValue();
                    numbers.add(number);
                    numberCount++;
                }
            }

            log.info("Прочитано {} чисел из {} строк в файле: {}", numberCount, rowCount, filePath);

        } catch (IOException e) {
            log.error("Ошибка чтения Excel файла: {}", filePath, e);
            throw new RuntimeException("Ошибка чтения Excel файла: " + e.getMessage(), e);
        }

        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

}
