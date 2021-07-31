package com.subtitle.transfer.service;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class OutputFile {
    //"C:\\Users\\Desktop\\world war 2.bcc"

    public boolean output(String finalFilePath,String combineOutput){
        File file = new File(finalFilePath);
        try {
            OutputStream outputStream = new FileOutputStream(file);
            byte[] bytes = combineOutput.getBytes();

            outputStream.write(bytes);

            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            System.out.println("导出成功");
            return true;
        }else{
            System.out.println("导出失败");
            return false;
        }
    }
}
