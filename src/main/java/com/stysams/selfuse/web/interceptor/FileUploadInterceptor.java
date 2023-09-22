package com.stysams.selfuse.web.interceptor;

import com.stysams.selfuse.web.file.FileType;
import com.stysams.selfuse.web.file.UploadProp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author wenjin
 * 文件上传全局拦截器
 */
@Slf4j
public class FileUploadInterceptor implements HandlerInterceptor {

    public static Set<String> limitFileTypeSet;
    public static List<FileType> fileTypeList;

    @Autowired
    UploadProp uploadProp;

    static {
        limitFileTypeSet = new HashSet<>();
        fileTypeList = new ArrayList<>();
        limitFileTypeSet.add("java");
        limitFileTypeSet.add("class");
        limitFileTypeSet.add("bat");
        limitFileTypeSet.add("exe");
        limitFileTypeSet.add("ini");
        //        limitFileTypeSet.add("sh");
        limitFileTypeSet.add("jsp");
        limitFileTypeSet.add("php");
        ///临时去掉jar后缀验证，解决数据处理机器人上传的相关问题。
        //        limitFileTypeSet.add("jar");
        fileTypeList.add(FileType.JAVA);
        fileTypeList.add(FileType.CLASS);
        fileTypeList.add(FileType.BAT);
        fileTypeList.add(FileType.EXE);
        fileTypeList.add(FileType.INI);
        fileTypeList.add(FileType.JSP);
        fileTypeList.add(FileType.JAR);
    }


    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // 判断是否为文件上传请求
        if (request instanceof MultipartHttpServletRequest multipartRequest) {
            Map<String, MultipartFile> files =
                    multipartRequest.getFileMap();
            //对多部件请求资源进行遍历
            for (String formKey : files.keySet()) {
                MultipartFile multipartFile =
                        multipartRequest.getFile(formKey);
                //判断允许上传文件白名单
                if (!checkAllowFile(multipartFile)) {
                    //log.error("{}:{}",ErrorCode.UN_ALLOW_FILE.getMsg(),multipartFile.getOriginalFilename());
                    //ErrorCode.UN_ALLOW_FILE.doThrow();
                }

                //判断是否为限制文件类型
                if (!checkFile(multipartFile)) {
                    //限制文件类型，请求转发到原始请求页面，并携带错误提示信息
                    //ErrorCode.UN_SAFE_FILE.doThrow();
                }
            }
        }
        return true;
    }

    /**
     * 检测允许上传文件白名单
     *
     * @param multipartFile
     * @return
     */
    private boolean checkAllowFile(MultipartFile multipartFile) {
        String suffix = multipartFile.getOriginalFilename();
        int index = 0;
        if (suffix != null) {
            index = suffix.lastIndexOf(".");
        }
        if (index >= 0) {
            if (suffix != null) {
                suffix = suffix.substring(index + 1);
            }
        }
        return uploadProp.isAllowFile(suffix);
    }

    /**
     * 先判断后缀，再判断真实文件类型
     *
     * @param multipartFile
     * @return
     */
    private boolean checkFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        int index = 0;
        if (originalFilename != null) {
            index = originalFilename.lastIndexOf(".");
        }
        if (index > 0) {
            String fileSuffix = originalFilename.substring(index + 1);
            if (limitFileTypeSet.contains(fileSuffix.trim().toLowerCase())) {
                return false;
            }
        }
        FileType fileType = fileType(multipartFile.getInputStream());
        for (FileType limitType : fileTypeList) {
            if (fileType == limitType) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断文件类型
     *
     * @return 文件类型
     */
    public static FileType fileType(InputStream is) throws IOException {

        String fileHead = getFileHead(is);
        if (fileHead == null || fileHead.isEmpty()) {
            return null;
        }
        fileHead = fileHead.toUpperCase();
        FileType[] fileTypes = FileType.values();

        for (FileType type : fileTypes) {
            if (fileHead.startsWith(type.getValue())) {
                return type;
            }
        }

        return null;
    }

    public static String getFileHead(InputStream is) throws IOException {
        byte[] b = new byte[28];
        try {
            is.read(b, 0, 28);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return bytesToHexString(b);
    }

    /**
     * 将文件头转换成16进制字符串
     *
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] src) {

        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
