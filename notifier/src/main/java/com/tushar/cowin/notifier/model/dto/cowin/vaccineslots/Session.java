package com.tushar.cowin.notifier.model.dto.cowin.vaccineslots;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Session {
    @JsonProperty("session_id")
    private String id;
    @JsonProperty("date")
    private String date;
    @JsonProperty("available_capacity")
    private int availableCapacity;
    @JsonProperty("min_age_limit")
    private int minAgeLimit;
    @JsonProperty("vaccine")
    private String vaccine;
    @JsonProperty("slots")
    private List<String> slots;



    @Override
    public String toString() {
        return "Session{" +
                "date='" + date + '\'' +
                ", availableCapacity=" + availableCapacity +
                '}';
    }
}
