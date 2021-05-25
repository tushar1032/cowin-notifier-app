package com.tushar.cowin.notifier.service.notification;

import com.tushar.cowin.notifier.model.dto.mail.MailContent;
import com.tushar.cowin.notifier.model.dto.mail.MailItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.tushar.cowin.notifier.commons.Constants.MAIL_SUBJECT;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendMail(MailContent mailContent) {
        SimpleMailMessage content = new SimpleMailMessage();
        content.setFrom("noreply@cowin.notifier.com");
        content.setTo(mailContent.getReceiver());
        content.setSubject(String.format(mailContent.getSubject(), mailContent.getMailItems().get(0).getAge()));
        StringBuilder textBuilder = new StringBuilder();
        mailContent.getMailItems().forEach(item -> {
            textBuilder.append("Center: ")
                    .append(item.getCenter())
                    .append("\nAge: ")
                    .append(item.getAge())
                    .append("\nPincode: ")
                    .append(item.getPincode())
                    .append("\nBlock: ")
                    .append(item.getBlockName())
                    .append("\n\nSessions Available: \n");

            item.getSessionInfoList().forEach(sessionInfo -> {
                textBuilder.append("\nDate: ")
                        .append(sessionInfo.getDate())
                        .append("\nVaccine: ")
                        .append(sessionInfo.getVaccine())
                        .append("\nAvailable Capacity: ")
                        .append(sessionInfo.getAvailableCapacity())
                        .append("\n\n");
            });
            textBuilder.append("**********");
            textBuilder.append("\n\n\n");
        });

        content.setText(textBuilder.toString());
        javaMailSender.send(content);
        log.info("Mail sent Successfully!");
    }


}
