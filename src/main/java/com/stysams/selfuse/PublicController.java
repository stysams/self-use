package com.stysams.selfuse;

import com.stysa.tool.UtilService;
import com.stysams.selfuse.spi.service.TeatLoadService;
import com.stysams.selfuse.spi.service.TestLoadService1;
import com.stysams.selfuse.starter.SelfStarterSpringService;
import lombok.SneakyThrows;
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
        // todo 123
        testLoadService1.executeSout();
    }

    @Autowired
    private UtilService utilService;

    @SneakyThrows
    @GetMapping("encode")
    public String encode(String url){
        return utilService.encodeUrl(url);
    }

    @Autowired
    private SelfStarterSpringService selfStarterSpringService;
    @GetMapping("print")
    public void print(String str){
        selfStarterSpringService.printStr(str);
    }

}
