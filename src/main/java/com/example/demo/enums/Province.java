package com.example.demo.enums;

public enum Province {
    AZARBAYEJAN_SHARGHI("آذر بایجان شرقی",1),
    AZARBAYEJAN_GHARBI("آذر بایجان غربی",2),
    ARDEBIL("اردبیل",3),
    ISFAHAN("اصفهان",4),
    ALBORZ("البرز",5),
    ILAM("ایلام",6),
    BUSHEHR("بوشهر",7),
    TEHRAN("تهران",8),
    CHAHAR_MAHAL_BAKHTIARI("چهارمحل بختیاری",9),
    KHORASAN_JINUBI("خرسان جنوبی",10),
    KHORASAN_SHOMALI("خراسان شمالی",11),
    KHUZESTAN("خوزستان",12),
    ZANJA("زنجان",13),
    SEMNAN("سمنان",14),
    SISTAN_BLUCHESTAN("سیستان بلوچستان",15),
    FARS("فارس",16),
    GHAZVIN("قزوین",17),
    GHOM("قم",18),
    KERMAN("کرمان",19),
    KERMANSHAH("کرمانشاه",20),
    KAHKYOYEH_VA_BOYER_AHMAD("کهگیلویه و بویراحمد",21),
    GOLESTAN("گلستان",22),
    GILAN("گیلان",23),
    LORESTAN("لرستان",24),
    MAZANDARAN("مازندران",25),
    MARKAZI("مرکزی",26),
    HORMOZGAN("هرمزگان",27),
    HAMEDAN("همدان",28),
    YAZD("یزد",29);

    private final String value;
    private final int id;

    Province(String value, int id) {
        this.value = value;
        this.id = id;
    }

    public int id() {
        return this.id;
    }


    public String value() {
        return this.value;
    }
}
