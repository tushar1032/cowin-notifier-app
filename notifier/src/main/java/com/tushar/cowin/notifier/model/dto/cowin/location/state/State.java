package com.tushar.cowin.notifier.model.dto.cowin.location.state;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class State {
    @JsonProperty("state_id")
    private String id;
    @JsonProperty("state_name")
    private String name;
}
