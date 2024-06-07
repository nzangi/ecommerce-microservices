package com.nzangi.ecommerce.notification;

import com.nzangi.ecommerce.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/*
*
* Replace PaymentConfirmation with Notification
*
* */

//@Repository
public interface NotificationRepository extends MongoRepository<Notification,String> {

}
