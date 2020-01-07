package com.jalon.demo.sys.controller;

import com.jalon.demo.sys.dto.UserDTO;
import com.jalon.demo.sys.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/web/demo")
public class DemoController {
    @Autowired
    private InitDbService initDbService;

    @PostMapping(value = "test.json")
    public UserDTO test() {
         initDbService.test();
         return new UserDTO();
    }
}
