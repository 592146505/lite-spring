package com.roamer.litespring.util;

/**
 * String 工具类
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/20 13:15
 */
public abstract class StringUtils {

    /**
     * 是否有长度
     *
     * @param str
     * @return
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    /**
     * 是否有长度
     *
     * @param str
     * @return
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * 是否是可见字符
     *
     * @param str
     * @return
     */
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    /**
     * 是否是可见字符
     *
     * @param str
     * @return
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 去除字符串中所有空格
     *
     * @param str
     * @return
     */
    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int index = 0;
        while (sb.length() > index) {
            if (Character.isWhitespace(sb.charAt(index))) {
                sb.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return sb.toString();
    }
}