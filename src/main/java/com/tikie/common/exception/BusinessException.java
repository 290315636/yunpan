package com.tikie.common.exception;

import com.tikie.common.i.IErrorCodeInteger;
import lombok.Data;

import java.util.Map;

/**
 * @author TiKie
 * @desc
 * @date 2018/11/3
 */
@Data
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private IErrorCodeInteger iErrorCode;

    private Map<String, Object> errorData;

    public BusinessException(IErrorCodeInteger iErrorCode) {
        super();
        this.iErrorCode = iErrorCode;
    }
}
