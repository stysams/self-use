package com.stysams.selfuse.web.file;

/**
 * 文件上传路径二次开发接口
 *
 * @author 杨伦
 * @since 10/26/21 10:16 AM
 */
public interface IUploadPathExt {

    /**
     * 最终上传路径：virtualPath/uploadBasePath/getExtUploadPath()/yyyyMMdd/
     * @return string
     */
    String getExtUploadPath();
}