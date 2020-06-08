package com.jt.service;

import com.jt.vo.FileImage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     *  图片上传
     * Request URL: http://localhost/pic/upload?dir=image
     * @param uploadFile
     * @return
     */
    public FileImage upload(MultipartFile uploadFile);
}
