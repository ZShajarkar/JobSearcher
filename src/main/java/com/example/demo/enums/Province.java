package com.example.demo.enums;

public enum Province {
    AZARBAYEJAN_SHARGHI("آذر بایجان شرقی"),
    AZARBAYEJAN_GHARBI("آذر بایجان غربی"),
    ARDEBIL("اردبیل"),
    ISFAHAN("اصفهان"),
    ALBORZ("البرز"),
    ILAM("ایلام"),
    BUSHEHR("بوشهر"),
    TEHRAN("تهران"),
    CHAHAR_MAHAL_BAKHTIARI("چهارمحل بختیاری"),
    KHORASAN_JINUBI("خرسان جنوبی"),
    KHORASAN_SHOMALI("خراسان شمالی"),
    KHUZESTAN("خوزستان"),
    ZANJA("زنجان"),
    SEMNAN("سمنان"),
    SISTAN_BLUCHESTAN("سیستان بلوچستان"),
    FARS("فارس"),
    GHAZVIN("قزوین"),
    GHOM("قم"),
    KERMAN("کرمان"),
    KERMANSHAH("کرمانشاه"),
    KAHKYOYEH_VA_BOYER_AHMAD("کهگیلویه و بویراحمد"),
    GOLESTAN("گلستان"),
    GILAN("گیلان"),
    LORESTAN("لرستان"),
    MAZANDARAN("مازندران"),
    MARKAZI("مرکزی"),
    HORMOZGAN("هرمزگان"),
    HAMEDAN("همدان"),
    YAZD("یزد");

    private final String value;

    Province(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
    }