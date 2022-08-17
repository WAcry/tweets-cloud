package com.ziyuan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class KafkaOperator {

    final static Logger logger = LoggerFactory.getLogger(KafkaOperator.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String key, String value) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, value);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("success");
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("failure", ex);
            }
        });
    }

    public void send(String topic, String value) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, value);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("success：" + result.toString());
            }

            @Override
            public void onFailure(Throwable e) {
                logger.error("error: " + e.getMessage());
            }
        });
    }
}
