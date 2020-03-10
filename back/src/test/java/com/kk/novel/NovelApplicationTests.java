package com.kk.novel;

import com.kk.novel.security.jwt.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NovelApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void TestMd5 () {
        String s = new String("123456");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + MD5Util.string2MD5(s));
        String md5 = MD5Util.string2MD5(s);
        System.out.println("加密的：" + MD5Util.convertMD5(md5));
        System.out.println("解密的：" + MD5Util.convertMD5(MD5Util.convertMD5(md5)));
    }
}
