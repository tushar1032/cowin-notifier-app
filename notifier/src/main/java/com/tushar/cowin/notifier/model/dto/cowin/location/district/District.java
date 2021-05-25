package com.tushar.cowin.notifier.model.dto.cowin.location.district;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class District {
    @JsonProperty("district_id")
    private String id;
    @JsonProperty("district_name")
    private String name;
}
