package com.gn3.kpc;

import com.gn3.kpc.service.CrawlingServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoConfig.class);
        CrawlingServiceImpl service = ac.getBean(CrawlingServiceImpl.class);
        service.newsCrawling();
    }
}
