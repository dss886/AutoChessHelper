package com.dss886.dotaautochess.utils;

/**
 * Created by dss886 on 2019/1/25.
 */
public class StringUtils {

    /**
     * Compatible method of String.join() in Java8
     */
    public static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements) {
        if (delimiter == null || elements == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (CharSequence cs: elements) {
            sb.append(cs);
            sb.append(delimiter);
        }
        if (sb.length() > delimiter.length()) {
            sb.delete(sb.length() - delimiter.length(), sb.length());
        }
        return sb.toString();
    }

}
