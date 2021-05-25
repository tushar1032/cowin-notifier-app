package com.tushar.cowin.notifier.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Enumerated;


@RequiredArgsConstructor
public enum Vaccine {

    COVAXIN("Covaxin"),
    COVISHIELD("Covishield"),
    BOTH("Both");

    @Getter
    private final String name;

}
