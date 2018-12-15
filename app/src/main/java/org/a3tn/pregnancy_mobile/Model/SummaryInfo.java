package org.a3tn.pregnancy_mobile.Model;

public class SummaryInfo {
    private int id;
    private String weeks;
    private String babyInfo;
    private String momInfo;
    private String picture;

    public SummaryInfo() {
    }

    public SummaryInfo(int id, String weeks, String babyInfo, String momInfo) {
        this.id = id;
        this.weeks = weeks;
        this.babyInfo = babyInfo;
        this.momInfo = momInfo;
    }

    public SummaryInfo(int id, String weeks, String babyInfo, String momInfo, String picture) {
        this.id = id;
        this.weeks = weeks;
        this.babyInfo = babyInfo;
        this.momInfo = momInfo;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getBabyInfo() {
        return babyInfo;
    }

    public void setBabyInfo(String babyInfo) {
        this.babyInfo = babyInfo;
    }

    public String getMomInfo() {
        return momInfo;
    }

    public void setMomInfo(String momInfo) {
        this.momInfo = momInfo;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
