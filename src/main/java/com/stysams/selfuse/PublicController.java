package com.stysams.selfuse;

import com.stysams.selfuse.spi.service.TeatLoadService;
import com.stysams.selfuse.spi.service.TestLoadService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
public class PublicController {

    @Autowired
    private TeatLoadService teatLoadService;
    @Autowired
    private TestLoadService1 testLoadService1;
    @GetMapping("sout")
    public void removeListener(){
        testLoadService1.executeSout();
    }


}
