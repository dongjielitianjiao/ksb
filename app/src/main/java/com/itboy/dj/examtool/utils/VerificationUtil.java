package com.itboy.dj.examtool.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/23.
 */

public class VerificationUtil {


    // 判断手机号码是否有效
    public static boolean isValidTelNumber(String telNumber) {
        if (!TextUtils.isEmpty(telNumber)) {
            String regex = "(\\+\\d+)?1[34578]\\d{9}$";
            return Pattern.matches(regex, telNumber);
        }

        return false;
    }

    // 判断邮箱地址是否有效
    public static boolean isValidEmailAddress(String emailAddress) {

        if (!TextUtils.isEmpty(emailAddress)) {
            String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
            return Pattern.matches(regex, emailAddress);
        }

        return false;
    }
}
