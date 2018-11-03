package com.tikie.file;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author TiKie
 * @desc
 * @date 2018/10/24
 */
@SpringBootTest
public class DemoTest {
    @Test
    public void test13() {
        int num[] = {1,2,3,1,1};
        Arrays.sort(num);
        for(int i:num){
            System.out.println("输出内容:"+ i);
        }

    }
}

