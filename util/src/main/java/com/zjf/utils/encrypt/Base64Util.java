package com.zjf.utils.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author : zhoujianfei
 * @description :
 * @date : 2017/11/20.
 */
public final class Base64Util {

    private Base64Util() {
        throw new AssertionError();
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) {
        try {
            return (new BASE64Decoder()).decodeBuffer(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}
