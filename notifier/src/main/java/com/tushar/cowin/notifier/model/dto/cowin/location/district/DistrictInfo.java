package com.tushar.cowin.notifier.model.dto.cowin.location.district;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DistrictInfo {
    @JsonProperty("districts")
    private List<District> districts;
}
