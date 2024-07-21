package cn.regexp.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@Slf4j
@ComponentScan(basePackages = "cn.regexp")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CodeAssistantApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeAssistantApplication.class, args);
        log.info("CodeAssistantApplication 启动成功！");
    }
}
