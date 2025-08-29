package com.tailan.payment.service.infra;

import com.tailan.payment.service.dtos.OrderResponse;
import com.tailan.payment.service.enums.ValidPaymentTypeEnum;
import com.tailan.payment.service.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private static final String TOPIC = "pedidos-para-pagamento";
    private final PaymentService  paymentService;

    public KafkaConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = TOPIC, groupId = "tailan-group")
    public void consumeOrderForPayment(OrderResponse orderResponse) {
        logger.info("Mensagem de pedido recebida para pagamento. ID do pedido: {}", orderResponse.id());

        paymentService.addPayment(orderResponse);

    }
}
