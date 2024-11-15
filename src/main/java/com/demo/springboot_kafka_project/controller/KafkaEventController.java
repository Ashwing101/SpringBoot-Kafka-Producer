package com.demo.springboot_kafka_project.controller;

import com.demo.springboot_kafka_project.service.KafkaMessagePublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/producer-app")
public class KafkaEventController {

    @Autowired
    private KafkaMessagePublisherService publisherService;

    @GetMapping("/publish/{msg}")
    public ResponseEntity<?> publishMessage(@PathVariable String msg){
        try {
            for(int i = 0; i< 1000; i++) {
                publisherService.sendMessagesToTopic(msg + " : " + i);
            }
return ResponseEntity.ok("Message send successfully ...");
        }catch (Exception e){

            System.out.println("Failed while sending the message");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
