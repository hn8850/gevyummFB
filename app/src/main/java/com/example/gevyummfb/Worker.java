package com.example.gevyummfb;

/**
 * @author : Harel Navon harelnavon2710@gmail.com
 * @version : 2.0
 * @since : 5.5.2022
 * This is the Java Class for the Workers Branch in the database.
 */

public class Worker {
    private String firstName;
    private String lastName;
    private String id;
    private String companyName;
    private String phone;
    private String active;

    public Worker() {
        this.firstName = "";
        this.lastName = "";
        this.id = "";
        this.companyName = "";
        this.phone = "";
        this.active = "";
    }

    public Worker(String firstName, String lastName, String id, String companyName, String phone, String active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.companyName = companyName;
        this.phone = phone;
        this.active = active;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
