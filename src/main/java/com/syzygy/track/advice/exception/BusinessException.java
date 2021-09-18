package com.syzygy.track.advice.exception;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data

public class BusinessException extends RuntimeException{

    private String errorCode;
    private String errorMessage;

}

