/**
 * 
 */
package com.tikie.file;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tikie.file.model.FileTree;
import com.tikie.file.service.FileTreeService;

/**
 * @author zhaocs
 * FileTreeService测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileTreeServiceTest {
	Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FileTreeService fileTreeService;
    
    @Test
    public void test() {
    	Boolean isTrue = fileTreeService.updateFileTreeFolderSize("f7d07e12c41947e787f6a01d042b8596", "42bf175b3cf448baa95019669c96e003", true);
    	Assert.assertTrue(isTrue);
    }
    
    @Test
    public void test1() {
        Map<String, Object> map = fileTreeService.getFileCountMap();
        logger.info(map.toString());
    }
}
