package com.gn3.kpc.crawler;

import com.gn3.kpc.dto.DTO;
import com.gn3.kpc.dto.NewsDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsCrawler implements Crawler, Serializable {

    @Override
    public List<String> prepareCrawling(WebDriver webDriver) {
        String rootUrl = "https://land.naver.com/news/headline.naver?page=";
        int pageCnt = 1;
        List<String> urls = new ArrayList<>();

        webDriver.get(rootUrl + pageCnt);
        List<WebElement> firstElements = webDriver.findElement(By.className("land_news_list")).findElements(By.className("news_item"));
        for (WebElement element : firstElements) {
            String s = element.findElement(By.tagName("a")).getAttribute("href").toString();
            urls.add(s);
        }
        pageCnt++;

        while(true){
            List<String> curUrls = new ArrayList<>();
            webDriver.get(rootUrl + pageCnt);
            List<WebElement> elements = webDriver.findElement(By.id("land_news_list")).findElements(By.className("news_item"));
            for (WebElement element : elements) {
                String s = element.findElement(By.tagName("a")).getAttribute("href").toString();
                curUrls.add(s);
            }
            pageCnt++;
            if(urls.get(urls.size()-1).equals(curUrls.get(curUrls.size()-1)))break;
            else{
                for (String curUrl : curUrls) {
                    urls.add(curUrl);
                }
            }
            if(curUrls.size() == 0) System.out.println(pageCnt-1);
        }
        return urls;
    }

    @Override
    public List<DTO> crawling(List<String> urls){
        List<DTO> dtos = new ArrayList<>();
        for (String url : urls) {
            try{
                Document doc = Jsoup.connect(url).get();
                String title = doc.getElementById("title_area").text();
                String content = doc.getElementById("dic_area").text();
                String joinDate = doc.getElementsByClass("media_end_head_info_datestamp_time _ARTICLE_DATE_TIME").get(0).text();

                NewsDTO newsDTO = new NewsDTO();
                newsDTO.setTitle(title);
                newsDTO.setContent(content);
                newsDTO.setJoinDate(joinDate);
                dtos.add(newsDTO);
            }catch (Exception e){
                System.out.println("error string = " + url);
            }
        }

        return dtos;
    }
}
