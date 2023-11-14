package com.gn3.kpc.service;

import com.gn3.kpc.crawler.Crawler;
import com.gn3.kpc.dto.DTO;
import org.apache.spark.api.java.JavaSparkContext;
import org.openqa.selenium.WebDriver;

import java.util.List;

public interface CrawlingService {
    boolean newsCrawling(Crawler crawler, WebDriver webDriver, JavaSparkContext javaSparkContext);
}
