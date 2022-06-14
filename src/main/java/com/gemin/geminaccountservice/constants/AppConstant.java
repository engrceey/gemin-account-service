package com.gemin.geminaccountservice.constants;

import lombok.Getter;

public class AppConstant {
    @Getter
    public enum Status {
        SUCCESSFUL("00"),
        ERROR("06"),
        REQUEST_IN_PROGRESS("09"),
        INSUFFICIENT_FUNDS("51"),
        SYSTEM_ERROR("96"),
        NOT_FOUND("56"),
        FORMAT_ERROR("30");

        private final String code;

        Status(String code) {
            this.code = code;
        }

    }
}