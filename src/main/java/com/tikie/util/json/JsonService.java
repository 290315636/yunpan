package com.tikie.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import javax.annotation.PostConstruct;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
public class JsonService implements IJsonService {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public JsonService() {
    }

    @PostConstruct
    public void init() {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public Map<String, Object> parseObject(String jsonStr) {
        return (Map)this.parseObject(jsonStr, Map.class);
    }

    public <T> T parseObject(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    public <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }

    public String toJSONString(Object object) {
        return JSON.toJSONString(object, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect});
    }

    public boolean isValidJson(String jsonStr) {
        try {
            JSON.parseObject(jsonStr);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public <T> byte[] serialize(T t) {
        if (t == null) {
            return new byte[0];
        } else {
            SerializeWriter out = new SerializeWriter();
            Throwable var3 = null;

            byte[] var5;
            try {
                JSONSerializer serializer = new JSONSerializer(out);
                serializer.config(SerializerFeature.SkipTransientField, false);
                serializer.config(SerializerFeature.WriteClassName, true);
                serializer.config(SerializerFeature.WriteMapNullValue, true);
                serializer.write(t);
                var5 = out.toString().getBytes(DEFAULT_CHARSET);
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (out != null) {
                    if (var3 != null) {
                        try {
                            out.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        out.close();
                    }
                }

            }

            return var5;
        }
    }

    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        if (bytes != null && bytes.length > 0) {
            String str = new String(bytes, DEFAULT_CHARSET);
            return JSON.parseObject(str, clazz);
        } else {
            return null;
        }
    }
}

