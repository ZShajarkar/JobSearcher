package com.example.demo.enums;

public enum JobGroup {
    SOFTWARE("وب،برنامه نویسی و نرم افزار"),
    PURCHASE("فروش و بازاریابی"),
    CONTENT_CREATION_AND_MANAGEMENT("تولید و مدیریت محتوا"),
    FINANCIAL_AND_ACCOUNTING("مالی و حسابداری"),
    OFFICE_WORKS("مسیول دفتر،احرایی و اداری"),
    DESIGNING("طراحی"),
    IT("IT/Devops/Server");
    private final String value;

    JobGroup(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
