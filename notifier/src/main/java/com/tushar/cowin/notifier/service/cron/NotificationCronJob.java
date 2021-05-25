package com.tushar.cowin.notifier.service.cron;

import com.tushar.cowin.notifier.model.dto.cowin.vaccineslots.Center;
import com.tushar.cowin.notifier.model.dto.mail.MailContent;
import com.tushar.cowin.notifier.model.dto.mail.MailItem;
import com.tushar.cowin.notifier.model.entity.AgeGroup;
import com.tushar.cowin.notifier.model.entity.NotificationEntry;
import com.tushar.cowin.notifier.model.entity.Vaccine;
import com.tushar.cowin.notifier.service.datafetcher.DataProcessingService;
import com.tushar.cowin.notifier.service.notification.NotificationSenderService;
import com.tushar.cowin.notifier.service.notificationentry.NotificationEntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tushar.cowin.notifier.commons.Constants.MAIL_SUBJECT;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationCronJob {

    private final NotificationSenderService notificationSenderService;
    private final NotificationEntryService notificationEntryService;
    private final DataProcessingService dataProcessingService;

    @Scheduled(fixedRate = 30000)
    public void for18WithCovishield() {
        log.info("Starting Cron Job for 18+ with Covishield");
        List<String> pincodePrefixes =
                notificationEntryService.getPincodePrefixes(18, Vaccine.COVISHIELD);
        Map<String, List<NotificationEntry>> entries = prepareEntriesMap(pincodePrefixes,Vaccine.COVISHIELD,AgeGroup.EIGHTEEN_PLUS);
        prepareDataAndSendNotification(entries);
    }

    @Scheduled(fixedRate = 30000)
    public void for18WithCovax() {
        log.info("Starting Cron Job for 18+ with Covaxin");
        List<String> pincodePrefixes =
                notificationEntryService.getPincodePrefixes(18, Vaccine.COVAXIN);
        Map<String, List<NotificationEntry>> entries = prepareEntriesMap(pincodePrefixes,Vaccine.COVAXIN,AgeGroup.EIGHTEEN_PLUS);
        prepareDataAndSendNotification(entries);
    }

    @Scheduled(fixedRate = 30000)
    public void for45WithCovax() {
        log.info("Starting Cron Job for 45+ with Covishield");
        List<String> pincodePrefixes =
                notificationEntryService.getPincodePrefixes(45, Vaccine.COVISHIELD);
        Map<String, List<NotificationEntry>> entries = prepareEntriesMap(pincodePrefixes,Vaccine.COVISHIELD,AgeGroup.FORTY_FIVE_PLUS);
        prepareDataAndSendNotification(entries);
    }

    @Scheduled(fixedRate = 30000)
    public void for45WithCovishield() {
        log.info("Starting Cron Job for 45+ with Covaxin");
        List<String> pincodePrefixes =
                notificationEntryService.getPincodePrefixes(45, Vaccine.COVAXIN);
        Map<String, List<NotificationEntry>> entries = prepareEntriesMap(pincodePrefixes,Vaccine.COVAXIN,AgeGroup.FORTY_FIVE_PLUS);
        prepareDataAndSendNotification(entries);
    }

    private void prepareDataAndSendNotification(Map<String, List<NotificationEntry>> entryList) {
        if(CollectionUtils.isEmpty(entryList)) {
            log.info("No Entries Present!");
            return;
        }
        entryList.forEach((pincode, entries) -> {
            entries.forEach(entry -> {
                String districtId = dataProcessingService.getDistrictId(entry.getLocation());
                List<Center> centers = dataProcessingService.getCenters(entry.getAgeGroup(), districtId, pincode);
                notificationSenderService.sendNotification(getMailContent(entry, centers));
            });
        });
    }

    private MailContent getMailContent(NotificationEntry entry, List<Center> centers) {
        List<MailItem> mailItemList = dataProcessingService.getCenters(entry.getAgeGroup(), centers);
        return new MailContent(mailItemList,MAIL_SUBJECT, entry.getEmail());
    }

    private Map<String, List<NotificationEntry>> prepareEntriesMap(List<String> pincodePrefixes, Vaccine vaccine, AgeGroup ageGroup) {
        Map<String, List<NotificationEntry>> entries = pincodePrefixes.stream()
                .collect(Collectors
                        .toMap(pin -> pin,
                                pin -> notificationEntryService
                                        .findEntryByPinCodePrefixAndVaccineAndAgeGroup(pin, vaccine, ageGroup)));
        return entries;
    }

}
