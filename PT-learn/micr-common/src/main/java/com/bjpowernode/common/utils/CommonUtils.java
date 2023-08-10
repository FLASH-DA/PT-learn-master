package com.bjpowernode.common.utils;

import java.util.regex.Pattern;

/**
 * Package:com.bjpowernode.common.util
 * Date:2022/3/3 14:57
 */
public class CommonUtils {

    /*处理pageNo*/
    public static int defaultPageNo(Integer pageNo) {
        int pNo = pageNo;
        if (pageNo == null || pageNo < 1) {
            pNo = 1;
        }
        return pNo;
    }

    /*处理pageSize*/
    public static int defaultPageSize(Integer pageSize) {
        int pSize = pageSize;
        if (pageSize == null || pageSize < 1) {
            pSize = 1;
        }
        return pSize;
    }

    /*手机号脱敏*/
    public static String tuoMinPhone(String phone) {
        String result = "***********";
        if (phone != null && phone.trim().length() == 11) {
            result = phone.substring(0, 3) + "******" + phone.substring(9, 11);
        }
        return result;
    }

    /**
     * 检查手机格式
     */
    public static boolean checkphone(String phone) {
        boolean flag = false;
        if (phone != null && phone.length() == 11) {
            flag = Pattern.matches("^1[1-9]\\d{9}$", phone);
        }
        return flag;
    }
}
