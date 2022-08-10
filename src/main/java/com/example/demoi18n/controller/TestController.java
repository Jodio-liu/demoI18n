package com.example.demoi18n.controller;

import com.example.demoi18n.i18n_utils.I18nMessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private I18nMessageUtil i18nMessageUtil;



    @GetMapping("/hello")
    public ResponseEntity<String> hello() {


//        applicationConfig.getBoolean("foo.test");
//        String res = I18nMessageUtil.getMessage("foo.test");
//        String ss = DecorationFirstTagEnum.DONATATION.getName();
//        System.out.println(ss);
        String reeee = I18nMessageUtil.getMessage("key.12345");

//        String value = CoinRecordSubAction.CAP.getValue();

        return new ResponseEntity<>(reeee, HttpStatus.OK);
    }

    @GetMapping("/pageHelper")
    public ResponseEntity<String> pageHelper() {
//        FireBaseUtil.pushSingle();

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
