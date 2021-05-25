package com.tushar.cowin.notifier.model.dto.response.find;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tushar.cowin.notifier.model.dto.response.notification_entry.NotificationEntryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class FindNotificationEntryDto {
    @JsonProperty("notification_entry")
    private final List<NotificationEntryDto> notificationEntries;

}
