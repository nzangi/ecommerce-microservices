package com.nzangi.ecommerce.kafka;

import com.nzangi.ecommerce.kafka.order.OrderConfirmation;
import com.nzangi.ecommerce.kafka.payment.PaymentConfirmation;
import com.nzangi.ecommerce.notification.Notification;
import com.nzangi.ecommerce.notification.NotificationRepository;
import com.nzangi.ecommerce.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class NotificationConsumer {
    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private final NotificationRepository notificationRepository;
//    private final EmailService emailService;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation){
        log.info("Consuming message from payment-topic "+paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)

                        .build()
        );

    }
    // todo send email

    @KafkaListener(topics = "order-topic")
    public void consumeOderConfirmationNotification(OrderConfirmation orderConfirmation){
        log.info("Consuming message from order-topic "+orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)

                        .build()
        );

    }
    // todo send email

}

