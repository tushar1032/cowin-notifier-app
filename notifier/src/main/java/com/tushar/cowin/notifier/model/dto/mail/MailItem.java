package com.tushar.cowin.notifier.model.dto.mail;

import java.util.List;
import java.util.Objects;

public class MailItem {
    private int age;
    private String center;
    private List<SessionInfo> sessionInfoList;
    private String pincode;
    private String blockName;

    public MailItem(int age, String center, List<SessionInfo> sessionInfoList, String pincode, String blockName) {
        this.age = age;
        this.center = center;
        this.sessionInfoList = sessionInfoList;
        this.pincode = pincode;
        this.blockName = blockName;
    }

    public int getAge() {
        return age;
    }

    public String getCenter() {
        return center;
    }

    public List<SessionInfo> getSessionInfoList() {
        return sessionInfoList;
    }

    public String getPincode() {
        return pincode;
    }

    public String getBlockName() {
        return blockName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailItem that = (MailItem) o;
        return Objects.equals(age, that.age) && Objects.equals(center, that.center) && Objects.equals(sessionInfoList, that.sessionInfoList) && Objects.equals(pincode, that.pincode) && Objects.equals(blockName, that.blockName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, center, sessionInfoList, pincode, blockName);
    }

    @Override
    public String toString() {
        return "MailContent{" +
                "age='" + age + '\'' +
                ", center='" + center + '\'' +
                ", sessionInfoList=" + sessionInfoList +
                ", pincode='" + pincode + '\'' +
                ", blockName='" + blockName + '\'' +
                '}';
    }
}
