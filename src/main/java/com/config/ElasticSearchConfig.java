package com.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public TransportClient esClient() throws UnknownHostException {

//        Settings settings = Settings.builder()
//                .put("cluster.name", "elasticsearch")
//                .put("client.transport.sniff", true)
//                .build();
//
//        TransportAddress master = new TransportAddress(InetAddress.getByName("47.75.53.11"), 9300);
//
//        TransportClient client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(master);

        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("47.75.53.11"), 9300));


        return client;
    }
}
