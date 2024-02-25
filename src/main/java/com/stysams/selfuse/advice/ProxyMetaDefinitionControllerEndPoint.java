package com.stysams.selfuse.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author StysaMS
 */
@RestControllerEndpoint(id = "proxy")
@RequiredArgsConstructor
public class ProxyMetaDefinitionControllerEndPoint {

    private final ProxyMetaDefinitionRepository proxyMetaDefinitionRepository;


    @GetMapping("listMeta")
    public List<ProxyMetaDefinition> getProxyMetaDefinitions(){
        return proxyMetaDefinitionRepository.getProxyMetaDefinitions();
    }

    @GetMapping("{id}")
    public ProxyMetaDefinition getProxyMetaDefinition(@PathVariable("id") String proxyMetaDefinitionId){
        return proxyMetaDefinitionRepository.getProxyMetaDefinition(proxyMetaDefinitionId);
    }

    @PostMapping("save")
    public String save(@RequestBody ProxyMetaDefinition definition){

        try {
            proxyMetaDefinitionRepository.save(definition);
            return "success";
        } catch (Exception e) {

        }
        return "fail";

    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id")String proxyMetaDefinitionId){
        try {
            proxyMetaDefinitionRepository.delete(proxyMetaDefinitionId);
            return "success";
        } catch (Exception e) {

        }
        return "fail";
    }

}