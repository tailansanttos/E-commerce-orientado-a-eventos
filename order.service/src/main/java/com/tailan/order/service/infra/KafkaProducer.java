package com.tailan.order.service.infra;

import com.tailan.order.service.dtos.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, OrderResponse> kafkaTemplate;
    private static final String TOPIC = "pedidos-para-pagamento";
    public static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, OrderResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderResponse orderResponse) {
        Message<OrderResponse> message = MessageBuilder
                .withPayload(orderResponse)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        kafkaTemplate.send(message);
        logger.info("Mensagem enviada para o topico '{}' com sucesso. Pedido ID: {}", TOPIC, orderResponse.id());
    }
}
