package com.tikie.util.error;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
public class ParamException extends RuntimeException {
    private final String errorCode;
    private final String errorMsg;

    public ParamException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public ParamException(String errorMsg) {
        this("error.validation", errorMsg);
    }

    public ErrorVM getErrorVM() {
        return new ErrorVM(this.errorCode, this.errorMsg);
    }
}

