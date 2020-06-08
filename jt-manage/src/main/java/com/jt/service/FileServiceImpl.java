package com.jt.service;

import com.jt.vo.FileImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/urlconfig.properties")
public class FileServiceImpl implements FileService {
    //  在本地创建一个存储图片的路径
    //本地磁盘
    @Value("${image.localDir}")
    String localDir;    // "D:/test";
    //虚拟路径
    @Value("${image.urlDie}")
    String urlDie ;     //"http://image.jt.com";


    public FileImage upload(MultipartFile uploadFile) {



        //获取文件名
        String filename = uploadFile.getOriginalFilename();
        //全部转换为小写
        filename = filename.toLowerCase();
        //正则匹配文件后缀
        StringBuffer sb;
        if (!filename.matches("^.+\\.(jpg|png|jpeg|gif)$")) {
            return FileImage.fail();
        }
        //校验是否是一个恶意程序
        try {
            // 转换为图像模板  如果异常  那么证明不是图片
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            int height = bufferedImage.getHeight();//获取高度
            int width = bufferedImage.getWidth();//获取宽度
            //判断图片尺寸是否正确
            if (height == 0 || width == 0) {
                return FileImage.fail();
            }
            // 根据当前日期 获取子路径
            String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
            StringBuffer houZui= new StringBuffer();
            houZui.append(dateDir).append(UUID.randomUUID().toString().replace("-","")).append(filename.substring(filename.lastIndexOf(".")));
//            //根据 图片 创建一个图片名称
            //全路径
            sb = new StringBuffer();
            sb.append(urlDie).append(houZui);
            File file = new File(new StringBuffer().append(localDir).append(houZui).toString());
            if (!file.exists()) {
                synchronized (FileService.class) {
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                }
            }
            uploadFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return FileImage.fail();
        }

        return FileImage.succeed(sb.toString(), 500, 500);
    }

}
