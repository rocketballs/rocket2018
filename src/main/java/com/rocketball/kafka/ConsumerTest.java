package com.rocketball.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerTest {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerTest.class);
    public static void main(String [] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "newsrec192.hz.163.org:9092,newsrec193.hz.163.org:9092,newsrec194.hz.163.org:9092");
        props.put("group.id", "test4");
        props.put("enable.auto.commit", "true");
//        props.put("auto.offset.reset", "earliest"); //需要新的group.id
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        TopicPartition topic = new TopicPartition("rocketball-topic-test",1);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.assign(Arrays.asList(topic));
        consumer.seek(topic,40);
//        consumer.seekToBeginning(Arrays.asList(topic));
//        for(int i=0;i<20;i++)
            System.out.println(consumer.position(new TopicPartition("rocketball-topic-test",1)));
//        consumer.subscribe(Arrays.asList("rocketball-topic-test","rocketball-topic-test-2"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofDays(3));
            for (ConsumerRecord<String, String> record : records)
                logger.info(record.toString());
        }
    }
}
