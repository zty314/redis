package com.example.redis;

import com.example.redis.controller.MinIOTemplate;
import io.minio.ObjectWriteResponse;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/10/27 16:44
 */
@SpringBootTest
public class MinIOTest {

    @Resource
    MinIOTemplate minTemplate;

    //测试创建bucket
    @Test
    public void testCreateBucket() throws Exception {
        minTemplate.makeBucket("test");
    }

    //测试上传文件对象
    @Test
    public void testPutObject() throws Exception {
        ObjectWriteResponse response = minTemplate.putObject("test",
                "base/我就想轻松一下.jpg",
                "C:\\Users\\maaya\\Pictures\\我就想轻松一下.jpg");
        System.out.println(response.object());
    }

    //测试删除文件对象
    @Test
    public void testDeleteObject() throws Exception {
        minTemplate.removeObject("test",
                "base/springboot青铜到王者封面.png");
    }
}