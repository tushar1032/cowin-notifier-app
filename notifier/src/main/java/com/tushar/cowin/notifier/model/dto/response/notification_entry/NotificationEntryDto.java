package com.tushar.cowin.notifier.model.dto.response.notification_entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tushar.cowin.notifier.model.dto.cowin.location.LocationDto;
import com.tushar.cowin.notifier.model.dto.response.add.NotificationEntryRequestDto;
import com.tushar.cowin.notifier.model.entity.AgeGroup;
import com.tushar.cowin.notifier.model.entity.Location;
import com.tushar.cowin.notifier.model.entity.NotificationEntry;
import com.tushar.cowin.notifier.model.entity.Vaccine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class NotificationEntryDto implements Serializable {

    @JsonProperty("id")
    private final Integer id;

    @JsonProperty("email")
    private final String email;

    @JsonProperty("pincodeprefix")
    private final String pinCodePrefix;

    @JsonProperty("vaccine")
    private final Vaccine vaccine;

    @JsonProperty("age_group")
    private final Integer ageGroup;
    @JsonProperty("location")
    private final LocationDto location;

    public static NotificationEntryDto of(NotificationEntry entry) {
        return new NotificationEntryDto(entry.getId(),entry.getEmail(),entry.getPinCodePrefix(),entry.getVaccine(), entry.getAgeGroup(), LocationDto.of(entry.getLocation()));
    }

}
