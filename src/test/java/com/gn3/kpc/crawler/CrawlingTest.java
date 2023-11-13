package com.gn3.kpc.crawler;

import com.gn3.kpc.AutoConfig;
import com.gn3.kpc.dto.DTO;
import com.gn3.kpc.dto.NewsDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class CrawlingTest {
    @Test
    void crawlingTest() throws IOException {
        /*AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoConfig.class);
        Crawler crawler = ac.getBean(NewCrawler.class);
        WebDriver bean = ac.getBean(WebDriver.class);
        List<String> strings = crawler.prepareCrawling(bean);
        for (String url : strings) {
            try{
                Document doc = Jsoup.connect(url).get();
                String title = doc.getElementById("title_area").text();
                String content = doc.getElementById("dic_area").text();
                String joinDate = doc.getElementsByClass("media_end_head_info_datestamp_time _ARTICLE_DATE_TIME").get(0).text();

                System.out.println("title = " + title);
                System.out.println("content = " + content);
                System.out.println("joinDate = " + joinDate);
            }catch (Exception e){
                System.out.println("error string = " + url);
            }
        }*/

    }
}
