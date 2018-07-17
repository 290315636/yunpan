/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月13日
 * 修改历史：
 * 		1、[2018年7月13日]创建文件 by zhaocs
 */
package com.tikie.file;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tikie.file.service.TestService;
import com.tikie.util.UUIDUtil;

/**
 * @author zhaocs
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestServiceTest {
Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private TestService testService;
    
    @Ignore
    public void test1() {
        // 测试10次登录，测试缓存是否生效
//      for(int i=0;i<10;i++) {
        com.tikie.file.bean.Test record = new com.tikie.file.bean.Test();
        record.setId(UUIDUtil.getUUID());
        record.setMsg("hello");
        record.setCtime(new Date());
            logger.info("==== {} ====", testService.insert(record));
//      }
    }
    
    @Test
    public void test2() {
        // 测试10次登录，测试缓存是否生效
        com.tikie.file.bean.Test record = new com.tikie.file.bean.Test();
        record.setId(UUIDUtil.getUUID());
        record.setMsg("hello tikie");
        record.setCtime(new Date());
            logger.info("==== {} ====", testService.insertSelective(record));
    }
}