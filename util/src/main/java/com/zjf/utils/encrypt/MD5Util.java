package com.zjf.utils.encrypt;

import sun.misc.Cleaner;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;

/**
 * @author : zhoujianfei
 * @description :
 * @date : 2017/11/20.
 */
public class MD5Util {
    public static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static MessageDigest mdInst = null;

    static {
        try {
            mdInst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String MD5(String context) {
        try {
            byte[] btInput = context.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String MD5By16(String context) {
        String ctx = MD5(context);
        try {
            return ctx.substring(8, 24);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密文件
     *
     * @param file
     * @return
     * @throws Exception
     * @author limin
     */
    public static String MD5File(File file) throws Exception {
        FileInputStream in = null;
        FileChannel ch = null;
        MappedByteBuffer byteBuffer = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            byteBuffer =
                    ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            mdInst.update(byteBuffer);
        } catch (Exception e) {
            throw new Exception("MD5加密异常!", e);
        } finally {
            if (byteBuffer != null) {
                byteBuffer.force();
                byteBuffer.clear();
                cleanBuffer(byteBuffer);
            }
            if (ch != null) {
                ch.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return bufferToHex(mdInst.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            char c0 = hexDigits[(bytes[l] & 0xf0) >> 4];
            char c1 = hexDigits[bytes[l] & 0xf];
            stringbuffer.append(c0);
            stringbuffer.append(c1);
        }
        return stringbuffer.toString();
    }

    public static void cleanBuffer(final Object buffer) throws Exception {
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            try {
                Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
                getCleanerMethod.setAccessible(true);
                Cleaner cleaner = (Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
                cleaner.clean();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
