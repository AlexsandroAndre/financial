package com.financial.report.infrastructure.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("financial")
public class TestController {

    @GetMapping("/overview")
    public void test(){

    }
}
