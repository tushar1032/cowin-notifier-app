package com.tushar.cowin.notifier.model.dto.cowin.location.state;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StateInfo {
    @JsonProperty("states")
    private List<State> states;
}
