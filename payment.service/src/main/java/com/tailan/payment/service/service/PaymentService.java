package com.tailan.payment.service.service;

import com.tailan.payment.service.dtos.OrderResponse;
import com.tailan.payment.service.dtos.PaymentResponse;
import com.tailan.payment.service.enums.ValidPaymentTypeEnum;
import com.tailan.payment.service.infra.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {
    private final KafkaProducer kafkaProducer;
    private final Random random=  new Random();
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    public void addPayment(OrderResponse orderResponse) {
        boolean isApproved = random.nextInt(100) < 70;
        ValidPaymentTypeEnum typePayment = ValidPaymentTypeEnum.PIX;
        PaymentStatusEnum statusFinal;

        if (isApproved) {
            statusFinal = PaymentStatusEnum.APPROVED;

            log.info("PAGAMENTO APROVADO para o pedido '{}'", orderResponse.id());
        }else {
            statusFinal = PaymentStatusEnum.RECUSED;
            log.info("PAGAMENTO REPROVADO para o pedido '{}'", orderResponse.id());
        }

        PaymentResponse paymentResponse =
                new PaymentResponse(orderResponse.id(), typePayment.name(),  statusFinal.name());

        kafkaProducer.sendMessage(paymentResponse);
    }

}
