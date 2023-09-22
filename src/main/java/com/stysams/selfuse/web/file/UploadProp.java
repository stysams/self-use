package com.stysams.selfuse.web.file;

import com.stysams.selfuse.web.util.SpringContextHolder;
import lombok.Getter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 上传文件属性配置
 *
 * @author wenjin
 */
public class UploadProp {

    /**
     * 虚拟目录地址
     */
    //@Value("${dsf.file.upload-virtual-path:}")
    private String virtualPath;

    /**
     * 上传文件根的相对虚拟目录的路径 必须/开头 /结尾
     */
    @Getter
    private final String uploadBasePath = "/upload/";

    /**
     * 下载文件 多文件下载时，压缩文件内还原源文件名字缓存目录
     */
    @Getter
    private final String downloadCacheBasePath = "/download/cache/";

    @Value("#{'${dsf.file.upload-virtual-path:}'.equals('')}")
    private boolean isNonVirtualPath;
    @Value("#{'${dsf.file.allow:txt,doc,docx,pdf,pptx,ppt,xls,xlsx,wps,jpg,png,gif,mp4,jpeg,mp3}'.split(',')}")
    private List<String> allowFileList;

    private Set<String> allowFileSet;

    @Getter
    public IUploadPathExt uploadPathExt;

    @Autowired
    public void setUploadPathExt(ObjectProvider<IUploadPathExt> uploadPathExt) {
        this.uploadPathExt = uploadPathExt.getIfAvailable();
    }

    public String getVirtualPath() {
        if (!StringUtils.hasText(this.virtualPath)) {
            this.virtualPath = SpringContextHolder.webRootDir();
        }
        return virtualPath;
    }

    public String getServerUrl() {
        String serverRootPath = SpringContextHolder.serverRootPath();
        if (serverRootPath.endsWith("/")) {
            serverRootPath = serverRootPath.substring(0, serverRootPath.length() - 1);
        }
        return serverRootPath;
    }


    public boolean isNonVirtualPath() {
        return isNonVirtualPath;
    }


    /**
     * 允许上传文件类型白名单
     *
     * @param suffix
     * @return
     */
    public boolean isAllowFile(String suffix) {
        if (this.allowFileList != null && this.allowFileList.size() > 0) {
            if (this.allowFileSet == null) {
                this.allowFileSet = new HashSet<>();
                for (String allowFile : this.allowFileList) {
                    //如果没配置白名单 默认会有一个长度为1的空字符串集合。
                    if (!StringUtils.hasText(allowFile) && this.allowFileList.size() == 1) {
                        this.allowFileList = new ArrayList<>();
                        return true;
                    } else {
                        this.allowFileSet.add(allowFile.toLowerCase(Locale.CHINA));
                    }
                }
            }
            return this.allowFileSet.contains(suffix.toLowerCase(Locale.CHINA));
        }
        return true;
    }
}
