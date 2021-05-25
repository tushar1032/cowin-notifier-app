package com.tushar.cowin.notifier.model.dto.response.add;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class AddEntryResponse implements Serializable {
    @JsonProperty("message")
    private final String message;
    @JsonProperty("notification_entry")
    private final NotificationEntryRequestDto notificationEntry;
}
