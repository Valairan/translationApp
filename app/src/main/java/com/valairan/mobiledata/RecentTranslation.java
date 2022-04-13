package com.valairan.mobiledata;

public class RecentTranslation {
    long id;
    String sourceLang;
    String targetLang;
    String sourcePhrase;
    String targetPhrase;

    public RecentTranslation(){

    }

    @Override
    public String toString() {
        return "RecentTranslation{" +
                "id=" + id +
                ", sourceLang='" + sourceLang + '\'' +
                ", targetLang='" + targetLang + '\'' +
                ", sourcePhrase='" + sourcePhrase + '\'' +
                ", targetPhrase='" + targetPhrase + '\'' +
                '}';
    }

    public RecentTranslation(long id, String sourceLang, String targetLang, String sourcePhrase, String targetPhrase) {
        this.id = id;
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
        this.sourcePhrase = sourcePhrase;
        this.targetPhrase = targetPhrase;
    }


    public RecentTranslation(String sourceLang, String targetLang, String sourcePhrase, String targetPhrase) {
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
        this.sourcePhrase = sourcePhrase;
        this.targetPhrase = targetPhrase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public void setTargetLang(String targetLang) {
        this.targetLang = targetLang;
    }

    public String getSourcePhrase() {
        return sourcePhrase;
    }

    public void setSourcePhrase(String sourcePhrase) {
        this.sourcePhrase = sourcePhrase;
    }

    public String getTargetPhrase() {
        return targetPhrase;
    }

    public void setTargetPhrase(String targetPhrase) {
        this.targetPhrase = targetPhrase;
    }
}
