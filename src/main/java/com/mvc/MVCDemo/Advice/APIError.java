package com.mvc.MVCDemo.Advice;

import com.mvc.MVCDemo.Cool.PrimeNumberValidation;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class APIError {
    private HttpStatus status;
    private String message;
    private List<String> suberror;
}
