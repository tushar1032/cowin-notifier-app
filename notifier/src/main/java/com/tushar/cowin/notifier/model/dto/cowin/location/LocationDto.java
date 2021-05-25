package com.tushar.cowin.notifier.model.dto.cowin.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tushar.cowin.notifier.model.entity.Location;
import lombok.Data;


@Data
public class LocationDto {
    @JsonProperty("state")
    private final String state;
    @JsonProperty("district")
    private final String district;

    public static LocationDto of(Location location) {
        return new LocationDto(location.getState(), location.getDistrict());
    }
}

