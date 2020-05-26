package com.lp.framework.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务
@MapperScan("com.lp.framework.manage.mapper")
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class,args);
    }

}
