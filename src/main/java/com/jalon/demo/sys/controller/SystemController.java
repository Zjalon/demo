package com.jalon.demo.sys.controller;

import com.jalon.demo.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/web/sys")
public class SystemController {
    @Autowired
    private SystemService systemService;

    @PostMapping(value = "getPermissionList.json")
    public void getPermissionList(){
        systemService.getPermissionList();
    }
}
