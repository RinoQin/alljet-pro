package org.alljet.epassport.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value(value = "${test.value}")
    private String testValue;

    @ResponseBody
    @RequestMapping(value="test.json")
    public Map<String,Object> test(){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("test",testValue);
        logger.info("sdfsdfsdfsdfsdf");
        return result;
    }

}
