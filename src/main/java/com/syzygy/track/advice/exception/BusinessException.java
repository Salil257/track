package com.syzygy.track.advice.exception;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class BusinessException extends RuntimeException{

    private String errorCode;
    private String errorMessage;

}

