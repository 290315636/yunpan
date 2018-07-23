package com.tikie.util.common;

public class SafeKit {
    public SafeKit() {
    }

    public static Integer getInteger(Object obj) {
        if (obj == null) {
            return null;
        } else {
            try {
                return Integer.valueOf(obj.toString());
            } catch (Exception var2) {
                return null;
            }
        }
    }

    public static Long getLong(Object obj) {
        if (obj == null) {
            return null;
        } else {
            try {
                return Long.valueOf(obj.toString());
            } catch (Exception var2) {
                return null;
            }
        }
    }

    public static Double getDouble(Object obj) {
        if (obj == null) {
            return null;
        } else {
            try {
                return Double.valueOf(obj.toString());
            } catch (Exception var2) {
                return null;
            }
        }
    }

    public static Boolean getBoolean(Object obj) {
        if (obj == null) {
            return null;
        } else {
            try {
                return Boolean.valueOf(obj.toString());
            } catch (Exception var2) {
                return null;
            }
        }
    }

    public static String getString(Object obj) {
        return obj == null ? null : obj.toString();
    }
}
