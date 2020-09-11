package com.example.redis;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2020/9/11 14:33
 */

//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest
@WebAppConfiguration
public class PwdDecOrEnc {
    @Test
    public void decrypt() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("l&id81!lw");
        encryptor.setConfig(config);
        String name = encryptor.encrypt("6379");
        String password = encryptor.encrypt("123456");

        //解密
        String mdName =encryptor.decrypt("1j85HnTXksumMD7A8P8q8w==");
        String mdPassword =encryptor.decrypt("xIJQ9OWQeDfsnk6i25uPSg==");
        System.out.println(name+"----------------"+mdName);
        System.out.println(password+"----------------"+mdPassword);
    }
    public static void main(String[] args) {
        new  PwdDecOrEnc().decrypt();
    }
}
