package com.cloud.gemini.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * created by fufan on 2019-07-12 14:55
 **/
@Data
@AllArgsConstructor
public class RateLimitException extends RuntimeException{

    Integer errorCode;
    String message;
    public RateLimitException(String message) {
        this.message = message;
    }


    @Getter
    public static enum RateLimitErrorEnum {

        TOO_MANY_REQUEST("too many request"),
        CONFIG_ERROR("config error");

        String description;

        RateLimitErrorEnum(String description) {
            this.description = description;
        }

    }
}
