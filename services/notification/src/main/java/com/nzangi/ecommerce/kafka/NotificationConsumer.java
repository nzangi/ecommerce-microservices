package com.nzangi.ecommerce.kafka;

import com.nzangi.ecommerce.email.EmailService;
import com.nzangi.ecommerce.kafka.order.OrderConfirmation;
import com.nzangi.ecommerce.kafka.payment.PaymentConfirmation;
import com.nzangi.ecommerce.notification.Notification;
import com.nzangi.ecommerce.notification.NotificationRepository;
import com.nzangi.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
/**
 * Notification Consumer from Kafka
 * */

@Service
@RequiredArgsConstructor

public class NotificationConsumer {
    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    // payment topic
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming message from payment-topic "+paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        // send email
        var customerName =  paymentConfirmation.customerFirstName() + "  "+ paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    // order-topic
    @KafkaListener(topics = "order-topic")
    public void consumeOderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming message from order-topic "+orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        //send email
        var customerName =  orderConfirmation.customer().firstname() + "  "+ orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );

    }

}

