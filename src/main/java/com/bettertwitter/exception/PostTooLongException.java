package com.bettertwitter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PostTooLongException extends RuntimeException {

    public PostTooLongException() {
        super();
    }

}
