package com.tikie.util;

import java.util.Random;

public class StringRandomUtil {
  
    //生成随机数字和字母,  
    public static String getStringRandom(int length) {
          
        String val = "";  
        Random random = new Random();
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
      
    public static void  main(String[] args) {  
        StringRandomUtil test = new StringRandomUtil();
        //测试  
        System.out.println(test.getStringRandom(16));
    }  
}  