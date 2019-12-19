package com.jalon.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoService {

    public String test() {
        log.error("error");
        return "test";
    }
}
