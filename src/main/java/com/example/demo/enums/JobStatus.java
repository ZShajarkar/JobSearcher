package com.example.demo.enums;

public enum JobStatus {
    UNDEFINED("نامشخص", 1),
    REJECTED("رد شده", 2),
    APPROVED("تایید شده", 3);

    private final int id;
    private final String value;

    JobStatus( String value,int id) {
        this.id = id;
        this.value = value;
    }


    public int id() {
        return this.id;
    }
    public int value() {
        return this.id;
    }
    public String getValue() {
        return value;
    }


}
