package com.tushar.cowin.notifier.model.dto.mail;

import java.util.Objects;

public class SessionInfo {
    private String date;
    private String vaccine;
    private int availableCapacity;

    public SessionInfo(String date, String vaccine, int availableCapacity) {
        this.date = date;
        this.vaccine = vaccine;
        this.availableCapacity = availableCapacity;
    }

    public String getDate() {
        return date;
    }

    public String getVaccine() {
        return vaccine;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInfo that = (SessionInfo) o;
        return Objects.equals(date, that.date) && Objects.equals(vaccine, that.vaccine) && Objects.equals(availableCapacity, that.availableCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, vaccine, availableCapacity);
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "date='" + date + '\'' +
                ", vaccine='" + vaccine + '\'' +
                ", availableCapacity='" + availableCapacity + '\'' +
                '}';
    }
}
