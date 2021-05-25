package com.tushar.cowin.notifier.service.notificationentry;

import com.tushar.cowin.notifier.model.dto.response.add.NotificationEntryRequestDto;
import com.tushar.cowin.notifier.model.dto.response.notification_entry.NotificationEntryDto;
import com.tushar.cowin.notifier.model.entity.AgeGroup;
import com.tushar.cowin.notifier.model.entity.NotificationEntry;
import com.tushar.cowin.notifier.model.entity.Vaccine;
import com.tushar.cowin.notifier.repository.NotificationEntryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static javax.transaction.Transactional.TxType.SUPPORTS;

@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationEntryService {

    private final NotificationEntryRepository notificationEntryRepository;

    @Transactional
    public boolean addEntry(NotificationEntryRequestDto notificationEntryRequestDto) {
        try {
            notificationEntryRepository.save(NotificationEntry.of(notificationEntryRequestDto));
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Transactional(value = SUPPORTS)
    public List<String> getPincodePrefixes(int ageGroup, Vaccine vaccine) {
        return notificationEntryRepository.getAllPincodePrefixByAgeGroupAndVaccine(ageGroup, vaccine);
    }

    @Transactional
    public boolean deleteEntry(int id) {
        try {
            notificationEntryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public List<NotificationEntry> findEntryByPinCodePrefixAndVaccineAndAgeGroup(String pinCodePrefix, Vaccine vaccine, AgeGroup ageGroup) {
        return notificationEntryRepository.getNotificationEntryByPinCodePrefixAndVaccineAndAgeGroup(pinCodePrefix, vaccine, ageGroup.getAge());
    }


}
