package com.crud.tasks.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {

    private RestTemplate restTemplate = new RestTemplate();

    private int counter = 0;
    @Scheduled(fixedDelay = 10 *  1000)
    public void doSomethingImportant(){
        System.out.println("aaa");
        System.out.println(counter++);
        System.out.println("\n\n\n");
        String googleContent = restTemplate.getForObject("http://www.lotto.pl/", String.class);
        System.out.println(googleContent);
    }

}
