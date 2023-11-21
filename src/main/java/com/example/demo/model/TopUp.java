package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TopUp {
    private String senum;
    private String scnum;
    private String expDate;

    public TopUp() {
    }

    public TopUp(String senum, String scnum, String expDate) {
        super();
        this.senum = senum;
        this.scnum = scnum;
        this.expDate = expDate;
    }

    public String getSenum() {
        return senum;
    }

    public void setSenum(String senum) {
        this.senum = senum;
    }

    public String getScnum() {
        return scnum;
    }

    public void setScnum(String scnum) {
        this.scnum = scnum;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return "TopUp [senum=" + senum + ", scnum=" + scnum + ", expDate=" + expDate + "]";
    }

}
