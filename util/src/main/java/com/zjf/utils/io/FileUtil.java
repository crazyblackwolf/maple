package com.zjf.utils.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: linziye
 * @description: 
 * @date: 15:34 2017/12/21 .
 */
public final class FileUtil {


    private FileUtil() {
        throw new AssertionError();
    }

    /**
     * @param source 源文件
     * @param target 目标文件
     */
    public static void copyFile(String source, String target) throws IOException {
        try (FileInputStream ins = new FileInputStream(source)) {
            try (FileOutputStream ous = new FileOutputStream(target);) {
                byte[] buf = new byte[1024 * 4];
                int len;
                while ((len = ins.read(buf)) != -1) {
                    ous.write(buf, 0, len);
                }
            }
        }
    }


    /**
     * @param source 源文件
     * @param target 目标文件
     */
    public static void copyFileByNIO(String source, String target) throws IOException {
        try (FileInputStream ins = new FileInputStream(source)) {
            try (FileOutputStream ous = new FileOutputStream(target);) {
                FileChannel insChannel = ins.getChannel();
                FileChannel ousChannel = ous.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(1024 * 4);
                while (insChannel.read(buffer) != -1) {
                    buffer.flip();
                    ousChannel.write(buffer);
                    buffer.clear();
                }
            }
        }
    }

    /**
     * 获取指定文件夹下面的所有文件
     *
     * @param filePath
     * @return 文件夹数组
     */
    public static File[] getSubFiles(String filePath) {
        File file = new File(filePath);
        if (!file.isDirectory()) {
            return null;
        }
        return file.listFiles();
    }

    /**
     * 在文件中是否存在key
     *
     * @param filePath 文件名
     * @param key      要查找的值
     * @return 存在：true;不存在:false
     * @throws IOException
     */
    public static boolean containsKey(String filePath, String key) throws IOException {
        return containsKey(new File(filePath), key);
    }

    /**
     * 在文件中是否存在key
     *
     * @param file 文件
     * @param key  要查找的值
     * @return 存在：true;不存在:false
     * @throws IOException
     */
    public static boolean containsKey(File file, String key) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.indexOf(key) != -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
