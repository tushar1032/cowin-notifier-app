package com.tushar.cowin.notifier.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AgeGroup {

    FORTY_FIVE_PLUS(45),
    EIGHTEEN_PLUS(18);

    @Getter
    private final int age;

    public static AgeGroup of(int age) {
        if(age == 18) {
            return EIGHTEEN_PLUS;
        }
        else if (age == 45) {
            return FORTY_FIVE_PLUS;
        }
        return null;
    }
}
