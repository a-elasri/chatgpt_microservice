package com.example.chatgpt_microservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.chatgpt_microservice.dtos.QADto;
import com.example.chatgpt_microservice.services.ChatgptService;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ChatgptMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatgptMicroserviceApplication.class, args);
    }

//    @Bean
//    CommandLineRunner start(ChatgptService chatgptService)
//    {
//        return args -> {
//            QADto out =chatgptService.sendPrompt("file","what is jenkins?");
//            System.out.println(out.getQuestion());
//            System.out.print(out.getAnswer());
//
//        };
//    }

}
