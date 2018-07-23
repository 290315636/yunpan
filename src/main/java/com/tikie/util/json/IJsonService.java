package com.tikie.util.json;

import java.util.List;
import java.util.Map;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
public interface IJsonService {
    Map parseObject(String var1);

    <T> T parseObject(String var1, Class<T> var2);

    <T> List<T> parseArray(String var1, Class<T> var2);

    String toJSONString(Object var1);

    boolean isValidJson(String var1);

    <T> byte[] serialize(T var1);

    <T> T deserialize(byte[] var1, Class<T> var2);
}

