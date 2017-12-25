package com.zjf.utils.string;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: linziye
 * @description:
 * @date: 15:34 2017/12/21 .
 */
public final class StringUtil {

    private StringUtil() {
        throw new AssertionError();
    }

    /**
     * 获取uuid,不带"-"
     *
     * @return java.lang.String
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().toUpperCase();
        return StringUtils.isBlank(uuidStr) ? "" : uuidStr.replace("-", "");
    }

    /**
     * @param str
     * @param regex
     * @return String[]
     */
    public static String[] split(String str, String regex) {
        if (StringUtils.isBlank(str)) {
            return null;
        } else if (StringUtils.isBlank(regex)) {
            String[] strs = new String[]{str};
            return strs;
        } else {
            return str.split(regex);
        }
    }

    /**
     * @param str
     * @return java.util.List
     */
    public static List<String> tranformStrToList(String str) {
        return tranformStrToList(str, ",");
    }

    /**
     * @param str
     * @param regex
     * @return java.util.List
     */
    public static List<String> tranformStrToList(String str, String regex) {
        List<String> list = new ArrayList();
        if (StringUtils.isBlank(str)) {
            return list;
        } else {
            if (str.indexOf(regex) == -1) {
                list.add(str);
            } else {
                String[] arr = str.split(regex);
                String[] arr$ = arr;
                int len$ = arr.length;
                for (int i$ = 0; i$ < len$; ++i$) {
                    String ele = arr$[i$];
                    list.add(ele.trim());
                }
            }
            return list;
        }
    }

    public static String join(List<?> arr, String seperator) {
        StringBuilder str = new StringBuilder();
        boolean isFirst = true;
        for (Object item : arr) {
            if (!isFirst) {
                str.append(seperator);
            }
            str.append(item);
            isFirst = false;
        }
        return str.toString();
    }
}
