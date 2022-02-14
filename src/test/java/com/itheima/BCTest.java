package com.itheima;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCTest {
    @Test
    public void testBC(){
        /**
         * md5 不安全
         * 加盐算法 也不安全
         * spring Security 动态加盐
         */
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode1 = bCryptPasswordEncoder.encode("123");
        String encode2 = bCryptPasswordEncoder.encode("123");
        Logger logger = LoggerFactory.getLogger(BCTest.class);
        logger.info("\n"+encode1+"\n"+encode2);
        boolean matches1 = bCryptPasswordEncoder.matches("123", encode1);
        boolean matches2 = bCryptPasswordEncoder.matches("123", encode2);
        System.out.println(matches1);
        System.out.println(matches2);
    }
}
