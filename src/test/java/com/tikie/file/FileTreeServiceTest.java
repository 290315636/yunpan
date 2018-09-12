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
    	FileTree record = new FileTree();
    	record.setFileId("d8383274ccd04745ae40ee825af69aa5");
    	record.setPid("1");
    	Boolean isTrue = fileTreeService.updateFileTreeFolderSize(record, true);
    	Assert.assertTrue(isTrue);
    }
    
    @Test
    public void test1() {
        Map<String, Object> map = fileTreeService.getFileCountMap();
        logger.info(map.toString());
    }
}
