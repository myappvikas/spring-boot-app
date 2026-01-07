package com.myapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse
{
    private String status;
    private LocalDateTime timestamp;
    private List<FieldErrorDetails> errors;
}
