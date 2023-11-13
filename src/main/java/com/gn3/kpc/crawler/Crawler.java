package com.gn3.kpc.crawler;

import com.gn3.kpc.dto.DTO;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

public interface Crawler {
    List<String> prepareCrawling(WebDriver webDriver);
    List<DTO> crawling(List<String> urls) throws IOException;
}
