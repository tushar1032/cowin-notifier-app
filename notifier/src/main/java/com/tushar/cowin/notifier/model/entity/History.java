package com.tushar.cowin.notifier.model.entity;

import com.tushar.cowin.notifier.model.dto.cowin.vaccineslots.Center;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.List;

@Table(name = "HISTORY_API_DATA")
@Entity
public class History {
    @SequenceGenerator(name = "history_id", sequenceName = "HISTORY_API_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_id")
    @Id
    private int id;

    @Column(name = "AGE_GROUP", nullable = false)
    private AgeGroup ageGroup;

    @Column(name = "PINCODE_PREFIX", nullable = false)
    private String pincodePrefix;

    private String hash = Strings.EMPTY;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getPincodePrefix() {
        return pincodePrefix;
    }

    public void setPincodePrefix(String pincode) {
        this.pincodePrefix = pincode;
    }

    public String getHash() {
        return hash;
    }

    @Column(name = "HASH", nullable = false)
    public void setHash(List<Center> centers) {
        this.hash = String.valueOf(centers.hashCode());
    }
}
