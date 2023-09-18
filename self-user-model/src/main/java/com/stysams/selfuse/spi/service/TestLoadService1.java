package com.stysams.selfuse.spi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestLoadService1 {

    @Autowired
    private TeatLoadService teatLoadService;

    public void executeSout(){
        teatLoadService.sout();
    }
}
