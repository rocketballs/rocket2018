package com.rocketball.elastic;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.util.Collections;
import java.util.Map;

import java.io.IOException;

public class EsClient2 {
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
        System.out.println("##########111############");

        Map<String, String> params = Collections.singletonMap("pretty", "true");
        Response response2 = restClient.performRequest("GET", "/", params);
        System.out.println("##########2222############");

        RestClientBuilder builder2 = RestClient.builder(new HttpHost("localhost", 9200))
                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder.setConnectTimeout(5000)
                                .setSocketTimeout(60000);
                    }
                })
                .setMaxRetryTimeoutMillis(60000);
    }

    public static void putRequest() throws IOException {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("newsrec192.hz.163.org", 9200, "http"),
                new HttpHost("newsrec193.hz.163.org", 9200, "http"),
                new HttpHost("newsrec194.hz.163.org", 9200, "http"));

        builder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(HttpHost host) {
                System.out.println("##########Fail############");
            }
        });
//        FileLock
        RestClient restClient = builder.build();
        Map<String, String> params = Collections.emptyMap();
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest("PUT", "/posts/doc/1", params, entity);
        response.getRequestLine().toString();
    }

}
