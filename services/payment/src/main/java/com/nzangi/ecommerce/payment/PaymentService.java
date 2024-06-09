package com.nzangi.ecommerce.payment;

import com.nzangi.ecommerce.notification.NotificationProducer;
import com.nzangi.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Payment Service
 */

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    private final NotificationProducer notificationProducer;

    private final PaymentMapper mapper;


    // Create Payment
    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}
