package com.app.savemoney;

public class Section {

    private String sectionName;
    private String sectionItem;


    public Section(String sectionName, String sectionItem) {
        this.sectionName = sectionName;
        this.sectionItem = sectionItem;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionItem() {
        return sectionItem;
    }

    public void setSectionItem(String sectionItem) {
        this.sectionItem = sectionItem;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", sectionItem='" + sectionItem + '\'' +
                '}';
    }
}
