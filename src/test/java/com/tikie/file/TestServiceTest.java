/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月13日
 * 修改历史：
 * 		1、[2018年7月13日]创建文件 by zhaocs
 */
package com.tikie.file;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tikie.common.CommonEnums.MQDestination;
import com.tikie.file.active.Producer;
import com.tikie.file.active.Publisher;
import com.tikie.file.service.TestService;
import com.tikie.util.UUIDUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    
    @Value("${tikie.project.upload.path}")
    private String path;
    
    @Resource
    Producer producer;
    
    @Resource
    Publisher publisher;
    
    @Test
    public void test1() {
        // 测试10次登录，测试缓存是否生效
//      for(int i=0;i<10;i++) {
        com.tikie.file.model.Test record = new com.tikie.file.model.Test();
        record.setId(UUIDUtil.getUUID());
        record.setMsg("hello");
        record.setCtime(new Date().toString());
            logger.info("==== {} ====", testService.insert(record));
//      }
    }
    
    @Ignore
    public void test2() {
        // 测试10次登录，测试缓存是否生效
        com.tikie.file.model.Test record = new com.tikie.file.model.Test();
        record.setId(UUIDUtil.getUUID());
        record.setMsg("hello tikie");
        record.setCtime(new Date().toString());
        logger.info("==== {} ====", testService.insertSelective(record));
    }

    @Test
//    public void testFindByPage() {
//        Page<com.tikie.file.model.Test> pages = testService.findByPage(1, 10);
//        PageInfo<com.tikie.file.model.Test> pageInfo = new PageInfo<>(pages);
//        Assert.assertNotNull(pages);
//        logger.info(pages.toString());    // 查询页数据
//        logger.debug(pageInfo.toString());// 所有页数据
//    }
    
    // 测试文件拷贝
    @Ignore
    public void uploadTest() throws Exception {
        File f = new File("C:/Users/zhaocs/Pictures/Saved Pictures/favicon.ico");
        FileCopyUtils.copy(f, new File(path+"/favicon.ico"));
    }
    
    // 测试文件仓库下的文件
    @Test
    public void listFilesTest() {
        File file = new File(path);
        for(File f : file.listFiles()) {
            System.out.println("fileName : "+f.getName());
        }
    }

    @Test
    public void test10() {
    	for(int i=0;i<10;i++) {
    		Map<String, Object> map = new HashMap<>();
    		map.put("name", "zhang" + i);
    		map.put("age", 20 + i);
    		map.put("sex", i%2==0?"男":"女");
    		
    		producer.send(MQDestination.FILE_QUEUE, map.toString());
    	}
    }
    
    @Test
    public void test11() {
    	for(int i=0;i<10;i++) {
    		publisher.publish("test.topic", "生产消息" + i);
    	}
    }
}
