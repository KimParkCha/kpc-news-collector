package com.gn3.kpc.service;

import com.gn3.kpc.crawler.Crawler;
import org.apache.spark.api.java.JavaSparkContext;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.ExecutionException;

public interface CrawlingService {
    boolean newsCrawling(Crawler crawler, WebDriver webDriver, JavaSparkContext javaSparkContext) throws ExecutionException, InterruptedException;
}
