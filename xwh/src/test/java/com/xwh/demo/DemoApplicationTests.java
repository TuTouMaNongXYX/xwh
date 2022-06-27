package com.xwh.demo;

import com.xwh.demo.arcsoft.FaceEngineMain;
import com.xwh.demo.cloud.alibaba.metaphysics.main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    FaceEngineMain faceEngineMain;

    @Test
    void contextLoads() throws IOException {
        main.fortuneTelling("20010128000000","谢","宇轩","男");

    }



}
