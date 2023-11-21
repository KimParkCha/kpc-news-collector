package com.gn3.kpc.crawler;

import com.gn3.kpc.dto.DTO;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public interface Crawler {
    List<String> prepareCrawling(WebDriver webDriver);
    List<DTO> crawling(List<String> urls) throws IOException;
}
