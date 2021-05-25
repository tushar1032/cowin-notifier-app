package com.tushar.cowin.notifier.model.entity;

import com.tushar.cowin.notifier.model.dto.cowin.location.LocationDto;
import lombok.Data;

import javax.persistence.*;

@Table(name = "LOCATION")
@Entity
@Data
public class Location {

    @SequenceGenerator(name = "location_id", sequenceName = "LOCATION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id")
    @Id
    private int id;

    @Column(name = "STATE", nullable = false)
    private String state;
    @Column(name = "DISTRICT", nullable = false)
    private String district;

    public static Location of(LocationDto locationDto) {
        Location location = new Location();
        location.setState(locationDto.getState());
        location.setDistrict(locationDto.getDistrict());
        return location;
    }
}
