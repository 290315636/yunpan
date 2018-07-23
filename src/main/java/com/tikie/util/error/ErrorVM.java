package com.tikie.util.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
public class ErrorVM implements Serializable {
    private final String errorCode;
    private final String resultMsg;
    private boolean isSuccess;
    private List<FieldErrorVM> fieldErrors;

    public ErrorVM(String errorCode) {
        this(errorCode, (String)null);
    }

    public ErrorVM(String errorCode, String resultMsg) {
        this.errorCode = errorCode;
        this.resultMsg = resultMsg;
        this.isSuccess = false;
    }

    public ErrorVM(String errorCode, String resultMsg, List<FieldErrorVM> fieldErrors) {
        this.errorCode = errorCode;
        this.resultMsg = resultMsg;
        this.fieldErrors = fieldErrors;
    }

    public void add(String objectName, String field, String errorMsg) {
        if (this.fieldErrors == null) {
            this.fieldErrors = new ArrayList();
        }

        this.fieldErrors.add(new FieldErrorVM(objectName, field, errorMsg));
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public List<FieldErrorVM> getFieldErrors() {
        return this.fieldErrors;
    }

    public boolean getIsSuccess() {
        return this.isSuccess;
    }
}

