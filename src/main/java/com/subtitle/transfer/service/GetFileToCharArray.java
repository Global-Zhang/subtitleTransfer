package com.subtitle.transfer.service;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class GetFileToCharArray {
    private static final String DEFAULT_ENCODING = "UTF-8";//编码
    private static final int PROTECTED_LENGTH = 51200;// 输入流保护 50KB
    //"D:\\Download\\Germany after the flooding _ DW Documentary - 中文（简体）.txt"

    public char[] fileToStream(String resourceFilePath){
        InputStream is;
        File file = new File(resourceFilePath);
        try {
            is = new FileInputStream(file);

            char[] chars = readInfoStream(is);

            return chars;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new char[0];
    }


    private static char[] readInfoStream(InputStream input) throws Exception {

        if (input == null) {
            throw new Exception("输入流为null");
        }
        //字节数组
        byte[] bcache = new byte[2048];
        int readSize = 0;//每次读取的字节长度
        int totalSize = 0;//总字节长度
        ByteArrayOutputStream infoStream = new ByteArrayOutputStream();
        try {
            //一次性读取2048字节
            while ((readSize = input.read(bcache)) > 0) {
                totalSize += readSize;
                if (totalSize > PROTECTED_LENGTH) {
                    throw new Exception("输入流超出50K大小限制");
                }
                //将fileToStream()数据写入infoStream
                infoStream.write(bcache,0,readSize);
            }
        } catch (IOException e1) {
            throw new Exception("输入流读取异常");
        } finally {
            try {
                //输入流关闭
                input.close();
            } catch (IOException e) {
                throw new Exception("输入流关闭异常");
            }
        }

        try {
            return infoStream.toString(DEFAULT_ENCODING).toCharArray();
        } catch (UnsupportedEncodingException e) {
            throw new Exception("输出异常");
        }
    }

}

