package com.tushar.cowin.notifier.model.dto.response.add;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tushar.cowin.notifier.model.dto.cowin.location.LocationDto;
import com.tushar.cowin.notifier.model.dto.response.notification_entry.NotificationEntryDto;
import com.tushar.cowin.notifier.model.entity.Vaccine;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NotificationEntryRequestDto {
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

    public static NotificationEntryRequestDto of(NotificationEntryDto notificationEntryDto) {
        return new NotificationEntryRequestDto(notificationEntryDto.getEmail(), notificationEntryDto.getPinCodePrefix(), notificationEntryDto.getVaccine(), notificationEntryDto.getAgeGroup(), notificationEntryDto.getLocation());
    }

}
