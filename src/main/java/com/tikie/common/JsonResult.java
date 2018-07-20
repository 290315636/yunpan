/**
 * 
 */
package com.tikie.common;

import java.io.Serializable;

/**
 * @author zhaocs
 *
 */
public class JsonResult implements Serializable{
	private static final long serialVersionUID = 2116963044670732331L;
	private int code;
    private String message;
    private Object data;

    public JsonResult(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public JsonResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
