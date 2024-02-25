package com.stysams.selfuse.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

/**
 * @author StysaMS
 */
@RequiredArgsConstructor
public class ProxyMetaDefinitionChangeListener {

    private final AopPluginFactory aopPluginFactory;

    @EventListener
    public void listener(ProxyMetaDefinitionChangeEvent proxyMetaDefinitionChangeEvent){
        ProxyMetaInfo proxyMetaInfo = aopPluginFactory.getProxyMetaInfo(proxyMetaDefinitionChangeEvent.getProxyMetaDefinition());
        switch (proxyMetaDefinitionChangeEvent.getOperateEventEnum()){
            case OperateEventEnum.ADD:
                aopPluginFactory.installPlugin(proxyMetaInfo);
                break;
            case OperateEventEnum.DEL:
                aopPluginFactory.uninstallPlugin(proxyMetaInfo.getId());
                break;
        }

    }
}