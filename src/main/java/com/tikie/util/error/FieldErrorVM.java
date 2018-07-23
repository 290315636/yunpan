package com.tikie.util.error;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
public class FieldErrorVM {
    private final String objectName;
    private final String field;
    private final String errorMsg;

    public FieldErrorVM(String dto, String field, String errorMsg) {
        this.objectName = dto;
        this.field = field;
        this.errorMsg = errorMsg;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public String getField() {
        return this.field;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
