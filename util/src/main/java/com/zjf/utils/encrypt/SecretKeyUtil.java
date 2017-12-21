package com.zjf.utils.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;


/**
 * @author : zhoujianfei
 * @description :
 * @date : 2017/11/20.
 */
public final class SecretKeyUtil {

    private SecretKeyUtil() {
        throw new AssertionError();
    }

    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
    final static byte[] defaultKeyBytes = {0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38
            , 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66
            , 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2};    //24字节的密钥;

    static {
        //添加新安全算法,如果用JCE就要把它添加进去
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    /**
     * keybyte为加密密钥，长度为24字节
     * src为被加密的数据缓冲区（源）
     *
     * @param keybyte
     * @param src
     * @return byte[]
     */
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try {
            if (keybyte == null || keybyte.length == 0) {
                keybyte = defaultKeyBytes;
            }
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 加密一个字符串
     *
     * @param src
     * @return byte[]
     */
    public static byte[] encryptMode(String src) {
        if (src == null) {
            return null;
        }
        return encryptMode(defaultKeyBytes, src.getBytes());
    }

    /**
     * 解密一个字符串
     *
     * @param src
     * @return byte[]
     */
    public static byte[] decryptMode(String src) {
        if (src == null) {
            return null;
        }
        return decryptMode(defaultKeyBytes, src.getBytes());
    }

    /**
     * 解密一个字符串
     *
     * @param src
     * @return byte[]
     */
    public static byte[] decryptMode(byte src[]) {
        return decryptMode(defaultKeyBytes, src);
    }


    /**
     * keybyte为加密密钥，长度为24字节
     * src为加密后的缓冲区
     *
     * @param keybyte
     * @param src
     * @return byte[]
     */
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try {
            if (keybyte == null || keybyte.length == 0) {
                keybyte = defaultKeyBytes;
            }
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }


    /**
     * 转换成十六进制字符串
     *
     * @param b
     * @return java.lang.String
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) {
                hs = hs + ":";
            }
        }
        return hs.toUpperCase();
    }
}
