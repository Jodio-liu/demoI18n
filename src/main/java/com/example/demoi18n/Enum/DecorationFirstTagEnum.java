package com.example.demoi18n.Enum;

import com.example.demoi18n.i18n_utils.I18nMessageUtil;

public enum DecorationFirstTagEnum {
    RECENTLY(-1, "message.key.30122"), MINE(-2, "message.key.20018"), DONATED(-3, "message.key.20019"), DONATATION(-4, "message.key.20020"), SHOP(-5, "message.key.30027"),
    RECOMMEND(-6, "message.key.30028");

    private long id;
    private String name;

    DecorationFirstTagEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return I18nMessageUtil.getMessage(name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
