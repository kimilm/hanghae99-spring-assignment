package com.kimilm.expert.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestApiException {
    private String errorMessage;

    private RestApiException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static RestApiException of (String errorMessage) {
        return new RestApiException(errorMessage);
    }
}
