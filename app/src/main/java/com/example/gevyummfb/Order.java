package com.example.gevyummfb;


/**
 * @author : Harel Navon harelnavon2710@gmail.com
 * @version : 2.0
 * @since : 5.5.2022
 * This is the Java Class for the Orders Branch in the database.
 */

public class Order {
    private String nameWV;
    private String nameFC;
    private Worker wv;
    private Company fc;
    private Meal ml;
    private String date;
    private String time;

    public Order() {

    }

    public Order(Worker wv, Company fc, Meal ml, String date, String time, String nameWV, String nameFC) {
        this.wv = wv;
        this.fc = fc;
        this.ml = ml;
        this.date = date;
        this.time = time;
        this.nameWV = nameWV;
        this.nameFC = nameFC;

    }

    public Worker getWv() {
        return wv;
    }

    public void setWv(Worker wv) {
        this.wv = wv;
    }

    public Company getFc() {
        return fc;
    }

    public void setFc(Company fc) {
        this.fc = fc;
    }

    public Meal getMl() {
        return ml;
    }

    public void setMl(Meal ml) {
        this.ml = ml;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNameWV() {
        return nameWV;
    }

    public void setNameWV(String nameWV) {
        this.nameWV = nameWV;
    }

    public String getNameFC() {
        return nameFC;
    }

    public void setNameFC(String nameFC) {
        this.nameFC = nameFC;
    }
}
