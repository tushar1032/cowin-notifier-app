package com.tushar.cowin.notifier.model.dto.cowin.vaccineslots;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Center implements Serializable {
    @JsonProperty("center_id")
    private String centerId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("state_name")
    private String stateName;
    @JsonProperty("district_name")
    private String districtName;
    @JsonProperty("block_name")
    private String blockName;
    @JsonProperty("pincode")
    private String pincode;
    @JsonProperty("address")
    private String address;
    @JsonProperty("sessions")
    private List<Session> sessions;

    @Override
    public String toString() {
        return "Center{" +
                "name='" + name + '\'' +
                ", sessions=" + sessions +
                '}';
    }


}
