package com.xwh.demo;

import com.xwh.demo.arcsoft.FaceEngineMain;
import com.xwh.demo.cloud.tencent.shortMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    FaceEngineMain faceEngineMain;

    @Test
    void contextLoads() {
        File file1 = new File("C:\\Users\\lenovo\\Desktop\\psc.png");
        File file2 = new File("C:\\Users\\lenovo\\Desktop\\psc3.png");
        faceEngineMain.faceRecognition(file1,file2);
    }

}
