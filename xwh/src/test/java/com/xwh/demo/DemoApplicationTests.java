package com.xwh.demo;

import com.xwh.demo.arcsoft.FaceEngineMain;
import com.xwh.demo.cloud.alibaba.identityCardVerification.verification;
import com.xwh.demo.cloud.tencent.shortMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    FaceEngineMain faceEngineMain;

    @Test
    void contextLoads() throws IOException {
        System.out.println( verification.is("430481200201229159","谢宇轩"));
    }



}
