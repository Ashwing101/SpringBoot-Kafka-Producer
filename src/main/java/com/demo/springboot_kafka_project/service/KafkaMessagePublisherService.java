package com.demo.springboot_kafka_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisherService {

   @Autowired
    private KafkaTemplate<String, Object> template;

   public void sendMessagesToTopic(String messages){


       CompletableFuture<SendResult<String, Object>> future = template.send("KafkaTopic-2", messages);
        future.whenComplete((result, ex) ->{

        if(ex == null){
            System.out.println("Sent Message=[" + messages +
                    "] with offset value =[" + result.getRecordMetadata().offset() + "]");

        }else {
            System.out.println("Unable to send Message=[" + messages +
            "] due to  =[" + ex.getMessage() + "]");
        }
        });


   }



}
