package com.example.controller;

import com.example.dto.ResponseNumber;
import com.example.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface NumberController {

    @Operation(
            summary = "Найти N-ное минимальное число",
            description = """
                    Ищет N-ное минимальное число в XLSX файле. 
                    Числа читаются из первого столбца файла, остальные столбцы игнорируются.
                    
                    ## Алгоритм:
                    - Используется эффективный алгоритм с max-heap
                    - Временная сложность: O(M log N), где M - количество чисел, N - порядковый номер
                    - Память: O(N) для хранения N минимальных элементов
                    
                    ## Примеры:
                    - Для массива [5, 3, 8, 1, 2] и n=3 результат будет 3
                    - Для массива [10, 20, 30] и n=1 результат будет 10
                    """,
            parameters = {
                    @Parameter(
                            name = "filePath",
                            description = "Абсолютный путь к XLSX файлу",
                            required = true,
                            example = "/home/user/data/numbers.xlsx",
                            schema = @Schema(type = "string", format = "path")
                    ),
                    @Parameter(
                            name = "n",
                            description = "Порядковый номер минимального числа (начинается с 1)",
                            required = true,
                            example = "3",
                            schema = @Schema(type = "integer", minimum = "1")
                    )
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный поиск N-ного минимального числа",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseNumber.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "data": 5
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные параметры запроса",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "success": false,
                                                "error": "Файл не существует: /invalid/path.xlsx"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "success": false,
                                                "error": "Внутренняя ошибка сервера"
                                            }
                                            """
                            )
                    )
            )
    })
    ResponseEntity<ResponseNumber> findNthMinimum(
            @RequestParam String filePath,
            @RequestParam int n);

}
