package com.lingDream.autoJavaFile;

import com.lingDream.autoJavaFile.view.AutoJavaFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource("classpath:autoJavaFile.xml")
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        AutoJavaFile bean = run.getBean(AutoJavaFile.class);
        bean.createFile();
    }
}
