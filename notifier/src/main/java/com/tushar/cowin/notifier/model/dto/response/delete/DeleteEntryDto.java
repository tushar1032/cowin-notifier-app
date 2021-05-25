package com.tushar.cowin.notifier.model.dto.response.delete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class DeleteEntryDto implements Serializable {
    @JsonProperty("message")
    private final String message;

}
