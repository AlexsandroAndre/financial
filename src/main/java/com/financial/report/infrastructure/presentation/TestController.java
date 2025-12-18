package com.financial.report.infrastructure.presentation;

import com.financial.report.infrastructure.presentation.version.ApiVersion;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiVersion("v1")
@RestController
@RequestMapping("/financial")
public class TestController extends BaseApiController{

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @PostConstruct
    void init() {
        log.info("TestController inicializado");
    }

    @GetMapping("/overview")
    public String test(){
        return "test";
    }
}
