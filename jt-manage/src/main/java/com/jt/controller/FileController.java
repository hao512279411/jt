package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.FileImage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

//文件上传 接收
@RestController
public class FileController {

    @Resource
    FileService fileService;

    @RequestMapping("/file")
    public String file(MultipartFile fileImage){
        //获取文件名
        String filename = fileImage.getOriginalFilename();
        //设置保存路径
        File filePath = new File("D:/test");
        //查看是否有此路径
        if (!filePath.exists()){
            //创建目录
            filePath.mkdirs();
        }
        try {
            //将文件输出到指定路径
            fileImage.transferTo(new File(filePath.getPath()+"/"+filename));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("路径不正确");
        }
        return "成功";
    }



    /**
     *  图片上传
     * Request URL: http://localhost/pic/upload?dir=image
     * @param uploadFile
     * @return
     */
    @RequestMapping("/pic/upload")
    public FileImage upload(MultipartFile uploadFile){
        return fileService.upload(uploadFile);



//        return FileImage.succeed("http://img1.imgtn.bdimg.com/it/u=3062374190,3164523231&fm=11&gp=0.jpg",500,500);
    }



}
