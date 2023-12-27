package org.wceng.entity;

public class Word {
    private String pinyin;
    private String zhuyin;
    private int bushouBihua;
    private int zongBihua;
    private String fanTiZi;
    private String hanziJiegou;
    private String jianTiBushou;
    private String zaoziFa;
    private String biShun;

    public Word(String pinyin, String zhuyin, int bushouBihua, int zongBihua, String fanTiZi,
                String hanziJiegou, String jianTiBushou, String zaoziFa, String biShun) {
        this.pinyin = pinyin;
        this.zhuyin = zhuyin;
        this.bushouBihua = bushouBihua;
        this.zongBihua = zongBihua;
        this.fanTiZi = fanTiZi;
        this.hanziJiegou = hanziJiegou;
        this.jianTiBushou = jianTiBushou;
        this.zaoziFa = zaoziFa;
        this.biShun = biShun;
    }

    // Getters and setters

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getZhuyin() {
        return zhuyin;
    }

    public void setZhuyin(String zhuyin) {
        this.zhuyin = zhuyin;
    }

    public int getBushouBihua() {
        return bushouBihua;
    }

    public void setBushouBihua(int bushouBihua) {
        this.bushouBihua = bushouBihua;
    }

    public int getZongBihua() {
        return zongBihua;
    }

    public void setZongBihua(int zongBihua) {
        this.zongBihua = zongBihua;
    }

    public String getFanTiZi() {
        return fanTiZi;
    }

    public void setFanTiZi(String fanTiZi) {
        this.fanTiZi = fanTiZi;
    }

    public String getHanziJiegou() {
        return hanziJiegou;
    }

    public void setHanziJiegou(String hanziJiegou) {
        this.hanziJiegou = hanziJiegou;
    }

    public String getJianTiBushou() {
        return jianTiBushou;
    }

    public void setJianTiBushou(String jianTiBushou) {
        this.jianTiBushou = jianTiBushou;
    }

    public String getZaoziFa() {
        return zaoziFa;
    }

    public void setZaoziFa(String zaoziFa) {
        this.zaoziFa = zaoziFa;
    }

    public String getBiShun() {
        return biShun;
    }

    public void setBiShun(String biShun) {
        this.biShun = biShun;
    }
}
