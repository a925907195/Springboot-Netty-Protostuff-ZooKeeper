package com.fjsh.rpc.boot;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/5/5.
 */
public class RpcBootstrap {

    public static void main(String[] arg){
        new ClassPathXmlApplicationContext("classpath:spring.xml");
    }
}
