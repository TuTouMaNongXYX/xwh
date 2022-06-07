package com.xwh.demo;

import com.xwh.demo.cloud.tencent.shortMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    shortMessage shortMessage;

    @Test
    void contextLoads() {
        String[] p ={"+8618975150649"};
        String[] o ={"123456"};
      shortMessage.sendMessages(p,o);
    }

}
