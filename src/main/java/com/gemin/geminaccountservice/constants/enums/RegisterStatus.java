package com.gemin.geminaccountservice.constants.enums;

import lombok.Getter;

@Getter
public enum RegisterStatus {
    SUCCESS("Created successfully"),
    FAILED("Account creation failed"),
    PENDING("Pending");

    private final String registrationStatus;

    RegisterStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
