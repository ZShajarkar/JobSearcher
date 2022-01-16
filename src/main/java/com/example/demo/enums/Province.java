package com.example.demo.enums;

public enum Province {
    AZARBAYEJAN_SHARGHI("آذر بایجان شرقی"),
    AZARBAYEJAN_GHARBI("آذر بایجان غربی"),
    ARDEBIL("اردبیل"),
    ISFAHAN("اصفهان"),
    ALBORZ("البرز"),
    ILAM("ایلام"),
    BUSHEHR("بوشهر"),
    TEHRAN("تهران");
    private final String value;

    Province(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}