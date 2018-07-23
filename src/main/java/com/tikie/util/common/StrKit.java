package com.tikie.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
public class StrKit {
    public static final String EMOJI_PATTERN = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[☀-⟿]";

    public StrKit() {
    }

    public static String camel2underscore(String camelName) {
        camelName = capitalize(camelName);
        String regex = "([A-Z][a-z]+)";
        String replacement = "$1_";
        String underscoreName = camelName.replaceAll(regex, replacement);
        underscoreName = underscoreName.toLowerCase().substring(0, underscoreName.length() - 1);
        return underscoreName;
    }

    public static String underscore2camel(String underscoreName) {
        String[] sections = underscoreName.split("_");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < sections.length; ++i) {
            String s = sections[i];
            if (i == 0) {
                sb.append(s);
            } else {
                sb.append(capitalize(s));
            }
        }

        return sb.toString();
    }

    public static String capitalize(String str) {
        return isEmpty(str) ? str : (new StringBuilder(str.length())).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    public static String lowerCase(String str) {
        return isEmpty(str) ? str : (new StringBuilder(str.length())).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    public static String toString(String[] strs) {
        StringBuilder sb = new StringBuilder();
        String[] var2 = strs;
        int var3 = strs.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String str = var2[var4];
            sb.append(str + ",");
        }

        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    public static boolean containsEmoji(String str) {
        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[☀-⟿]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
