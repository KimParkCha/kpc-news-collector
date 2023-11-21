package com.gn3.kpc;

import com.gn3.kpc.crawler.Crawler;
import com.gn3.kpc.crawler.NewsCrawler;
import com.gn3.kpc.service.CrawlingServiceImpl;
import org.apache.spark.api.java.JavaSparkContext;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;


public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoConfig.class);

        Crawler crawler = ac.getBean(NewsCrawler.class);
        WebDriver webDriver = ac.getBean(WebDriver.class);
        JavaSparkContext javaSparkContext = ac.getBean(JavaSparkContext.class);
        CrawlingServiceImpl service = ac.getBean(CrawlingServiceImpl.class);

        service.newsCrawling(crawler, webDriver, javaSparkContext);
    }
}
