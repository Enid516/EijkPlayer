package com.enid.eijkplayer.bean;

import org.json.JSONObject;

/**
 * Created by big_love on 2017/2/22.
 */

public class Creator {
    private String id;
    private String level;
    private String gender;
    private String nick;
    private String portrait;

    public Creator(JSONObject jsonObject) {
        this.id = jsonObject.optString("id");
        this.level = jsonObject.optString("level");
        this.gender = jsonObject.optString("gender");
        this.nick = jsonObject.optString("nick");
        this.portrait = jsonObject.optString("portrait");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
