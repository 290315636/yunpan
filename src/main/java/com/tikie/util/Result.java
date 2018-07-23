package com.tikie.util;

import com.tikie.util.common.SafeKit;
import com.tikie.util.common.StrKit;
import com.tikie.util.error.ParamException;
import com.tikie.util.json.JsonKit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
@ApiModel
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -4569216979022946969L;
    @ApiModelProperty(
            notes = "返回数据",
            required = true
    )
    private T data;
    @ApiModelProperty(
            notes = "成功标识",
            example = "true",
            required = true
    )
    private boolean isSuccess = false;
    @ApiModelProperty(
            notes = "结果信息",
            example = "执行成功！",
            required = true
    )
    private String resultMsg = "执行失败!";

    public Result() {
    }

    public static <T> Result<T> fail() {
        return new Result();
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> result = fail();
        result.setResultMsg(msg);
        return result;
    }

    public static <T> Result<T> success(String msg) {
        Result<T> result = success();
        result.setResultMsg(msg);
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result();
        result.setResultMsg("执行成功！");
        result.setIsSuccess(true);
        return result;
    }

    public static <T> Result<T> success(T t) {
        Result<T> result = success();
        result.setData(t);
        return result;
    }

    public static <T> Result<T> success(Result<T> result) {
        if (result == null) {
            return success();
        } else {
            result.setResultMsg("执行成功！");
            result.setIsSuccess(true);
            return result;
        }
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getIsSuccess() {
        return this.isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String toString() {
        return JsonKit.toJSONString(this);
    }

    public Result addData(String key, Object value) {
        Object map;
        if (this.data == null) {
            map = new HashMap();
        } else {
            if (!(this.data instanceof Map)) {
                throw new ParamException("not support");
            }

            map = (Map)this.data;
        }

        ((Map)map).put(key, value);
        this.setData((T) map);
        return this;
    }

    public Result removeData(String... keys) {
        if (this.data != null && this.data instanceof Map) {
            Map<String, Object> map = (Map)this.data;
            String[] var3 = keys;
            int var4 = keys.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String key = var3[var5];
                map.remove(key);
            }

            return this;
        } else {
            return this;
        }
    }

    public Result clearData() {
        this.data = null;
        return this;
    }

    public Object get(String key) {
        if (this.data != null && !StrKit.isEmpty(key) && this.data instanceof Map) {
            Map<String, Object> map = (Map)this.data;
            return map.get(key);
        } else {
            return null;
        }
    }

    public String getString(String key) {
        return SafeKit.getString(this.get(key));
    }

    public Integer getInteger(String key) {
        return SafeKit.getInteger(this.get(key));
    }

    public Long getLong(String key) {
        return SafeKit.getLong(this.get(key));
    }

    public Double getDouble(String key) {
        return SafeKit.getDouble(this.get(key));
    }

    public Boolean getBoolean(String key) {
        return SafeKit.getBoolean(this.get(key));
    }
}
