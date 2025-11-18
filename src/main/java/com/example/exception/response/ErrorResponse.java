package com.example.exception.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Ответ ошибки API")
public class ErrorResponse {

    @Schema(description = "Статус ошибки")
    private HttpStatus status;

    @Schema(description = "Сообщение ошибки")
    private String message;

    public int getStatus() {
        return status.value();
    }

}

