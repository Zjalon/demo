package com.jalon.demo.sys.controller;

import com.jalon.demo.sys.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
    @Autowired
    private InitDbService initDbService;

    @GetMapping(value = "test")
    public void test() {
         initDbService.test();
    }
}
