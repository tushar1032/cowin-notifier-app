package com.tushar.cowin.notifier.repository;

import com.tushar.cowin.notifier.model.entity.NotificationEntry;
import com.tushar.cowin.notifier.model.entity.Vaccine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NotificationEntryRepository extends CrudRepository<NotificationEntry,Integer> {

    public List<NotificationEntry> getNotificationEntryByPinCodePrefixAndVaccineAndAgeGroup(String pinCodePrefix, Vaccine vaccine, int ageGroup);

    @Query("select u.pinCodePrefix from NotificationEntry u where u.ageGroup = ?1 and u.vaccine = ?2")
    public List<String> getAllPincodePrefixByAgeGroupAndVaccine(int ageGroup, Vaccine vaccine);

}
