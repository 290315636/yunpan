package com.tikie.util.json;

import com.tikie.util.BeanKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
public class JsonKit {
    private static final Logger logger = LoggerFactory.getLogger(JsonKit.class);

    public JsonKit() {
    }

    public static Map<String, Object> parseObject(String jsonStr) {
        return getJsonService().parseObject(jsonStr);
    }

    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        return getJsonService().parseObject(jsonStr, clazz);
    }

    public static <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
        return getJsonService().parseArray(jsonStr, clazz);
    }

    public static boolean isValidJson(String jsonStr) {
        return getJsonService().isValidJson(jsonStr);
    }

    public static String toJSONString(Object object) {
        return getJsonService().toJSONString(object);
    }

    private static IJsonService getJsonService() {
        Object jsonService;
        if (BeanKit.getApplicationContext() == null) {
            jsonService = new JsonService();
            logger.warn("请初始化spring后再使用JsonKit");
        } else {
            jsonService = (IJsonService)BeanKit.getBean(IJsonService.class);
        }

        return (IJsonService)jsonService;
    }
}
