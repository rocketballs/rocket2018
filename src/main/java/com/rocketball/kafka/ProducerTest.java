package com.rocketball.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;

public class ProducerTest {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "newsrec192.hz.163.org:9092,newsrec193.hz.163.org:9092,newsrec194.hz.163.org:9092");
//        props.put("metadata.broker.list", "newsrec192.hz.163.org:9092,newsrec193.hz.163.org:9092,newsrec194.hz.163.org:9092");
//        props.put("zookeeper.connect", "newsrec41.xs.163.org:2181,newsrec42.xs.163.org:2181,newsrec43.xs.163.org:2181,newsrec44.xs.163.org:2181,newsrec50.xs.163.org:2181");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("num.partitions", "6");
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 40; i <50; i++)
            producer.send(new ProducerRecord<String, String>("rocketball-topic-test",4, "hi:"+Integer.toString(i), Integer.toString(i)));

        producer.close();

    }
}
