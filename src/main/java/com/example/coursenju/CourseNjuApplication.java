package com.example.coursenju;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseNjuApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseNjuApplication.class, args);
    }

}
