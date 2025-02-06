package com.anyex.apps.system.utils;

/**
 * @Author
 * @Date 2023/11/14 9:22
 */
public class PasswordUtil {
    //数字
    public static final String REG_NUMBER = ".*\\d+.*";
    //大写字母
    public static final String REG_UPPERCASE = ".*[A-Z]+.*";
    //小 写字母
    public static final String REG_LOWERCASE = ".*[a-z]+.*";
    //特殊符号
    public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

    public static boolean checkPassword(String password) {
        //密码为空或者长度小于8位则返回false
        if (password == null || password.length() < 8) {
            return false;
        }
        int i = 0;
        if (password.matches(REG_NUMBER)) {
            i++;
        }
        if (password.matches(REG_LOWERCASE)) {
            i++;
        }
        if (password.matches(REG_UPPERCASE)) {
            i++;
        }
        if (password.matches(REG_SYMBOL)) {
            i++;
        }
        return i >= 3;
    }

//    public static void main(String[] args) {
//        boolean zm5c2OZL = checkPassword("ZM5c2OZL");
//        System.out.println(zm5c2OZL);
//    }
}
