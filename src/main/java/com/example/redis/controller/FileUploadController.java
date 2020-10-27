package com.example.redis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/10/25 17:24
 */
@RestController
public class FileUploadController {

    //绑定文件上传路径到uploadPath
    @Value("${web.upload-path}")
    private String uploadPath;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile,
                         HttpServletRequest request) throws IOException {

        // 在 uploadPath 文件夹中通过日期对上传的文件归类保存
        // 比如：/2019/06/06/cf13891e-4b95-4000-81eb-b6d70ae44930.png
        String format = sdf.format(new Date());
        File folder = new File(uploadPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        // 对上传的文件重命名，避免文件重名
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());


        // 文件保存
        uploadFile.transferTo(new File(folder, newName));

        // 返回上传文件的访问路径
        //https://localhost:8888/2020/10/18/a9a05df4-6615-4bb5-b859-a3f9bf4bfae0.jpg
        String filePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + "/" + format + newName;
        return filePath;
    }

}