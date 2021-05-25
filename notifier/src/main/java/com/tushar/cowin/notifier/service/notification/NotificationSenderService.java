package com.tushar.cowin.notifier.service.notification;

import com.tushar.cowin.notifier.commons.Constants;
import com.tushar.cowin.notifier.model.dto.mail.MailContent;
import com.tushar.cowin.notifier.model.dto.mail.MailItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationSenderService {

    private final MailSenderService mailSenderService;

    public void sendNotification(MailContent mailContent) {
        if (!CollectionUtils.isEmpty(mailContent.getMailItems())) {
            log.info("Sending Mail.....");
            mailSenderService.sendMail(mailContent);
        } else {
            log.info("There are no new centers available!!");
        }
    }
}
