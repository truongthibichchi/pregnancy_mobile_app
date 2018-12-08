package org.a3tn.pregnancy_mobile.Model;

public class SummaryInfo {
    int id;
    String weeks;
    String babyInfo;
    String momInfo;

    public SummaryInfo() {
    }

    public SummaryInfo(int id, String weeks, String babyInfo, String momInfo) {
        this.id = id;
        this.weeks = weeks;
        this.babyInfo = babyInfo;
        this.momInfo = momInfo;
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
}
