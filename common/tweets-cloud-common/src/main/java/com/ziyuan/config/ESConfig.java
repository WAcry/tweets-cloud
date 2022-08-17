package com.ziyuan.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ESConfig {
    @Value("${elasticsearch.cluster-nodes:}")
    private String[] clusterNodes;
    @Value("${elasticsearch.cluster-name}")
    private String clusterName;

    /**
     * solve netty issue for elasticsearch
     */
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        Settings esSetting = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", true)
                .put("thread_pool.search.size", 5)
                .build();
        TransportClient client = new PreBuiltTransportClient(esSetting);
        for (String clusterNode : clusterNodes) {
            String[] split = clusterNode.split(":");
            client.addTransportAddress(new TransportAddress(InetAddress.getByName(split[0]), Integer.parseInt(split[1])));
        }
        return new ElasticsearchTemplate(client);
    }
}