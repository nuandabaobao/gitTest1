package com.tbjj.portal.common.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ebiz on 2017/7/20.
 */
public class SecurityUtil {

//    public static final int BCRYPT_PASSWORD_ENCODER_STRENGTH = 4;
//
//    private static BCryptPasswordEncoder bcrypt() {
//        return new BCryptPasswordEncoder(BCRYPT_PASSWORD_ENCODER_STRENGTH);
//    }
//
//    public static PasswordEncoder getBCryptPasswordEncoder() {
//        return bcrypt();
//    }

    public static String EncodeByMd5(String str) {
        String result =null;
        try {
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            result = base64en.encode(md5.digest(str.getBytes("utf-8")));
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkpassword(String newpasswd, String oldpasswd){
        if (EncodeByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
}
