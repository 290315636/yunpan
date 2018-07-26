package com.tikie.file.zhangshitai;

import java.io.File;

/**
 * @author zhangshitai
 * @date 2018-07-25
 */
public class FileTest {
    public static void main(String[] args) {
        try {
            File file = File.createTempFile("name",".txt",new File("c:/zcs"));
            if (null == file){
                file.getAbsolutePath();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
