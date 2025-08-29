package com.tailan.payment.service.infra;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("pagamentos-concluidos")
                .replicas(1)
                .partitions(1)
                .build();
    }
}
