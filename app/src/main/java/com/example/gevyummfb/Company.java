package com.example.gevyummfb;

/**
 * @author : Harel Navon harelnavon2710@gmail.com
 * @version : 2.0
 * @since : 5.5.2022
 * This is the Java Class for the Companies Branch in the database.
 */

public class Company {
    private String name;
    private String taxNumber;
    private String mainPhone;
    private String secPhone;
    private String active;

    public Company() {
        this.name = "";
        this.taxNumber = "";
        this.mainPhone = "";
        this.secPhone = "";
        this.active = "";
    }

    public Company(String name, String taxNumber, String mainPhone, String secPhone, String active) {
        this.name = name;
        this.taxNumber = taxNumber;
        this.mainPhone = mainPhone;
        this.secPhone = secPhone;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getSecPhone() {
        return secPhone;
    }

    public void setSecPhone(String secPhone) {
        this.secPhone = secPhone;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }


}
