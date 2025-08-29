package com.tailan.payment.service.infra;

import com.tailan.payment.service.dtos.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String TOPIC = "pagamentos-concluidos";
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(PaymentResponse paymentResponse) {
        Message<PaymentResponse> message = MessageBuilder.withPayload(paymentResponse)
                        .setHeader(KafkaHeaders.TOPIC, TOPIC)
                                .build();
        kafkaTemplate.send(message);
        log.info("Mensagem enviada para o topico '{}' com sucesso. Pedido ID: {}", TOPIC, paymentResponse.orderId());
    }

}
