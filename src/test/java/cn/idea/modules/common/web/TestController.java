package cn.idea.modules.common.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() throws Exception {
        String msg = "hello world!";
        logger.info("_____msg={}",msg);
    }
}
