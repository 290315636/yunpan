package com.tikie.common.enums;

import com.tikie.common.i.IErrorCodeInteger;
import lombok.Getter;

/**
 * @author TiKie
 * @desc
 * @date 2018/11/3
 */
@Getter
public enum SysErrorEnums implements IErrorCodeInteger {
    /**参数为空*/
    EMPTY_PARAME("A11002", "参数为空"),
    /**参数错误*/
    ERROR_PARAME("A11002", "参数错误");

    private String errorCode;
    private String errorMessage;

    private SysErrorEnums(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
