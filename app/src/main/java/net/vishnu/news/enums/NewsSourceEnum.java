package net.vishnu.news.enums;

public enum NewsSourceEnum {

    UNKNOWN(-1),
    GENERAL(0),
    BUSiNESS(1),
    ENTERTAINMENT(2),
    HEALTH(3),
    SCIENCE(4),
    SPORTS(5),
    TECHNOLOGY(6);

    private int id;

    NewsSourceEnum(int id) {
        this.id = id;
    }

    public static NewsSourceEnum getNewsSourceEnum(int id) {
        for (NewsSourceEnum newsSourceEnum : NewsSourceEnum.values()) {
            if (newsSourceEnum.id == id) {
                return newsSourceEnum;
            }
        }
        return UNKNOWN;
    }

}
