package com.example.controller.impl;

import com.example.controller.NumberController;
import com.example.dto.ResponseNumber;
import com.example.service.impl.NumberServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/numbers")
@RequiredArgsConstructor
@Tag(name = "Number Service API", description = "API для поиска N-ного минимального числа в XLSX файлах")
public class NumberControllerImpl implements NumberController {

    private final NumberServiceImpl numberService;

    @GetMapping("/nth-minimum")
    public ResponseEntity<ResponseNumber> findNthMinimum(
            @RequestParam String filePath,
            @RequestParam int n) {

        log.info("Поиск {}-го минимального числа в файле: {}", n, filePath);

        return ResponseEntity.ok(numberService.findNthMinimum(filePath, n));
    }

}
