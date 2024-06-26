package com.nzangi.ecommerce.email;

import com.nzangi.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//  Email Service


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    // DI of mailSender and templateEngine
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    /**
     * Async sendPaymentSuccessEmail Method
     */
    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper =
                new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        // Email send from
        messageHelper.setFrom("nzangimuoki284@gmail.com");

        final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();

        Map<String,Object> variables = new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("amount",amount);
        variables.put("orderReference",orderReference);
        log.info("INFO - PAYMENT ORDER REFERENCE IS :  {}",orderReference);



        Context context = new Context();
        context.setVariables(variables);

        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate =   templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("INFO - PAYMENT CONFIRMATION successfully send to {} with template {} ",destinationEmail,templateName);

        }catch (MessagingException exception){
            log.info("WARNING - Cannot send E-mail to {}",destinationEmail);
        }

    }

    /**
     * Async sendOrderConfirmationEmail Method
     */
    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,

            List<Product> products

    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        // Email send from
        messageHelper.setFrom("nzangimuoki284@gmail.com");

        // Get the ORDER CONFIRMATION template
        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();

        //Map the ORDER CONFIRMATION variables

        Map<String,Object> variables = new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("amount",amount);
        variables.put("orderReference",orderReference);
        variables.put("products",products);

        // logging information
        log.info("INFO - ORDER REFERENCE IS :  {}",orderReference);



        Context context = new Context();
        context.setVariables(variables);

        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate =   templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("INFO - ORDER CONFIRMATION successfully send to {} with template {}",destinationEmail,templateName);

        }catch (MessagingException exception){
            log.info("WARNING - Cannot send E-mail to {}",destinationEmail);
        }

    }
}
