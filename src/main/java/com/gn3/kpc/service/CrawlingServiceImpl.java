package com.gn3.kpc.service;

import com.gn3.kpc.crawler.Crawler;
import com.gn3.kpc.dto.DTO;
import org.apache.hadoop.shaded.com.nimbusds.jose.shaded.json.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Component
public class CrawlingServiceImpl implements CrawlingService, Serializable {

    @Override
    public  boolean newsCrawling(Crawler crawler, WebDriver webDriver, JavaSparkContext javaSparkContext) {
        List<String> strings = crawler.prepareCrawling(webDriver);
        JavaRDD<String> rdd = javaSparkContext.parallelize(strings, 3);
        JavaRDD<DTO> partitionSums = rdd.mapPartitions((Iterator<String> iter) -> {
            List<String> urls = new ArrayList<>();
            iter.forEachRemaining(urls::add);
            return crawler.crawling(urls).iterator();
        });

        partitionSums.foreachPartition((Iterator<DTO> iter)->{
            List<DTO> newsDTO = new ArrayList<>();
            iter.forEachRemaining(newsDTO::add);
            Properties props = new Properties();
            props.put("bootstrap.servers","master:9092,sn01:9092,sn02:9092,sn03:9092");
            props.put("key.serializer" , "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer" , "org.apache.kafka.common.serialization.StringSerializer");
            KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

            for (DTO dto : newsDTO) {
                JSONObject dtoInfo = dto.DTOInfo();
                ProducerRecord<String, String> record = new ProducerRecord<>("news",
                        dtoInfo.toString() + "\n");
                RecordMetadata metadata = kafkaProducer.send(record).get();
            }
        });

        Properties props = new Properties();
        props.put("bootstrap.servers","master:9092,sn01:9092,sn02:9092,sn03:9092");
        props.put("key.serializer" , "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer" , "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = new ProducerRecord<>("news", "end\n");

        javaSparkContext.close();
        return true;
    }


}
