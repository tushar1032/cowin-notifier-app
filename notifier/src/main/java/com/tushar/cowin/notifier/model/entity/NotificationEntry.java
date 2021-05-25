package com.tushar.cowin.notifier.model.entity;

import com.tushar.cowin.notifier.model.dto.response.add.NotificationEntryRequestDto;
import com.tushar.cowin.notifier.model.dto.response.notification_entry.NotificationEntryDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "NOTIFICATION_ENTRY_TABLE")
@Entity
@Data
public class NotificationEntry {

    @SequenceGenerator(name = "notifier_entry_id", sequenceName = "NOTIFIER_ENTRY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notifier_entry_id")
    @Id
    private int id;

    @Column(name = "EMAIL_ID", nullable = false)
    private String email;

    @Column(name = "PINCODE_PREFIX", nullable = false)
    private String pinCodePrefix;

    @Column(name = "VACCINE_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Vaccine vaccine;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION", referencedColumnName = "id", nullable = false)
    private Location location;

    private int ageGroup;

    @Column(name = "AGE_GROUP", nullable = false)
    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup.getAge();
    }

    public static NotificationEntry of(NotificationEntryRequestDto notificationEntryRequestDto) {
        NotificationEntry notificationEntry = new NotificationEntry();
        notificationEntry.setEmail(notificationEntryRequestDto.getEmail());
        notificationEntry.setAgeGroup(AgeGroup.of(notificationEntryRequestDto.getAgeGroup()));
        notificationEntry.setVaccine(notificationEntryRequestDto.getVaccine());
        notificationEntry.setPinCodePrefix(notificationEntryRequestDto.getPinCodePrefix());
        notificationEntry.setLocation(Location.of(notificationEntryRequestDto.getLocation()));
        return notificationEntry;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntry that = (NotificationEntry) o;
        return id == that.id && ageGroup == that.ageGroup && Objects.equals(email, that.email) && Objects.equals(pinCodePrefix, that.pinCodePrefix) && vaccine == that.vaccine && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, pinCodePrefix, vaccine, location, ageGroup);
    }
}

