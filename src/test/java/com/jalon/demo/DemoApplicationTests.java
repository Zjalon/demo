package com.jalon.demo;

import com.jalon.demo.sys.service.InitDbService;
import com.jalon.demo.sys.service.DemoUserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    InitDbService initDbService;
    @Autowired
    DemoUserDetailService demoUserDetailService;
    @Test
    void contextLoads() {
        initDbService.initDb();
//        demoUserDetailService.loadUserByUsername("user");
    }

}
