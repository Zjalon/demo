package com.jalon.demo.sys.controller;

import com.jalon.demo.sys.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping(value = "test")
    public void test() {
         demoService.initDb();
    }
}
