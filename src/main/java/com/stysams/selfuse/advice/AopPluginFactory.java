package com.stysams.selfuse.advice;

import java.util.NoSuchElementException;

/**
 * @author StysaMS
 */
public class AopPluginFactory {
    public void installPlugin(ProxyMetaInfo proxyMetaInfo){
        if(StringUtils.isEmpty(proxyMetaInfo.getId())){
            proxyMetaInfo.setId(proxyMetaInfo.getProxyUrl() + SPIILT + proxyMetaInfo.getProxyClassName());
        }
        AopUtil.registerProxy(defaultListableBeanFactory,proxyMetaInfo);
    }

    public void uninstallPlugin(String id){
        String beanName = PROXY_PLUGIN_PREFIX + id;
        if(defaultListableBeanFactory.containsBean(beanName)){
            AopUtil.destoryProxy(defaultListableBeanFactory,id);
        }else{
            throw new NoSuchElementException("Plugin not found: " + id);
        }
    }
}
