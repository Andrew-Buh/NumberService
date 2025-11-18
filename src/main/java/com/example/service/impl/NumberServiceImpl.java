package com.example.service.impl;

import com.example.dto.ResponseNumber;
import com.example.mapper.NumberMapper;
import com.example.service.NumberService;
import com.example.util.ExcelFileProcessor;
import com.example.util.NumberFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
public class NumberServiceImpl implements NumberService {

    private final ExcelFileProcessor excelFileProcessor;
    private final NumberFinder numberFinder;
    private final NumberMapper numberMapper;

    public ResponseNumber findNthMinimum(String filePath, int n) {
        validateInput(filePath, n);

        log.debug("Обработка файла: {} для поиска {}-го минимального числа", filePath, n);

        int[] numbers = excelFileProcessor.readNumbersFromFile(filePath);

        log.info("Найдено {}-е минимальное число: {} в файле: {}", n, numberFinder.findNthMinimum(numbers, n),
                filePath);

        return numberMapper.toSuccessResponse(numberFinder.findNthMinimum(numbers, n));
    }

    private void validateInput(String filePath, int n) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не может быть пустым");
        }

        if (!filePath.toLowerCase().endsWith(".xlsx")) {
            throw new IllegalArgumentException("Файл должен быть в формате XLSX");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не существует: " + filePath);
        }

        if (n <= 0) {
            throw new IllegalArgumentException("N должно быть положительным числом");
        }
    }

}
