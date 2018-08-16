package org.alljet.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractException extends Exception {

    private String errCode;

    public AbstractException(String errCode,String message){
        super(message);
        this.errCode = errCode;
    }
}
