package com.enid.eijkplayer.bean;

/**
 * Created by big_love on 2017/2/22.
 */

import org.json.JSONObject;

/**
 *{
 creator: {
 id: 116112386,
 level: 8,
 gender: 0,
 nick: "yuyuyu～",
 portrait: "http://img2.inke.cn/MTQ4NzI0MTgwNzgxNSM4NjgjanBn.jpg"
 },
 id: "1487738368838036",
 name: "",
 city: "北京市",
 share_addr: "http://mlive.inke.cn/share/live.html?uid=116112386&liveid=1487738368838036&ctime=1487738368",
 stream_addr: "http://pull4.a8.com/live/1487738368838036.flv",
 version: 0,
 slot: 3,
 live_type: "",
 landscape: 0,
 optimal: 0,
 group: 60,
 distance: "2.0km",
 link: 0,
 multi: 0,
 rotate: 0,
 extra: {
 cover: null,
 label: [
 {
 tab_name: "北京市",
 tab_key: "北京市",
 cl: [
 0,
 216,
 201,
 1
 ]
 }
 ]
 }
 },
 */

public class Lives {
    private Creator creator;
    private String id;
    private String name;
    private String city;
    private String share_addr;
    private String stream_addr;
    private String version;
    private String slot;
    private String live_type;
    private String landscape;
    private String optimal;
    private String group;
    private String distance;
    private String link;
    private String multi;
    private String rotate;
    private String extra;

    public Lives(JSONObject jsonObject) {
        this.creator = new Creator(jsonObject.optJSONObject("creator"));
        this.id = jsonObject.optString("id");
        this.name = jsonObject.optString("name");
        this.city = jsonObject.optString("city");
        this.stream_addr = jsonObject.optString("stream_addr");
        this.distance = jsonObject.optString("distance");
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShare_addr() {
        return share_addr;
    }

    public void setShare_addr(String share_addr) {
        this.share_addr = share_addr;
    }

    public String getStream_addr() {
        return stream_addr;
    }

    public void setStream_addr(String stream_addr) {
        this.stream_addr = stream_addr;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getLive_type() {
        return live_type;
    }

    public void setLive_type(String live_type) {
        this.live_type = live_type;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public String getOptimal() {
        return optimal;
    }

    public void setOptimal(String optimal) {
        this.optimal = optimal;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMulti() {
        return multi;
    }

    public void setMulti(String multi) {
        this.multi = multi;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
