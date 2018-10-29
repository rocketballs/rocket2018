package com.rocketball.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class EsClient {
    public static void main(String[] args) throws IOException {
        System.out.println("###########");
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("newsrec192.hz.163.org", 9200, "http")
                /*new HttpHost("newsrec193.hz.163.org", 9200, "http"),
                new HttpHost("newsrec194.hz.163.org", 9200, "http")*/);

//        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
        builder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(HttpHost host) {
                System.out.println("##########Fail############");
            }
        });
//        FileLock
        RestClient restClient = builder.build();
        Response response = restClient.performRequest("GET", "/");
//        FileInputStream is = new FileInputStream(response.getEntity().getContent());
//        System.out.println(response.getEntity().getContent());
        System.out.println("######################");
    }
}
