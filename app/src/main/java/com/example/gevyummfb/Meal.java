package com.example.gevyummfb;

import java.io.Serializable;

/**
 * @author : Harel Navon harelnavon2710@gmail.com
 * @version : 2.0
 * @since : 5.5.2022
 * This is the Java Class for the Meals Branch in the database.
 */

public class Meal implements Serializable {
    private String appetizer;
    private String mainDish;
    private String side;
    private String dessert;
    private String drink;


    public Meal() {
        this.appetizer = "";
        this.mainDish = "";
        this.side = "";
        this.dessert = "";
        this.drink = "";
    }

    public Meal(String appetizer, String mainDish, String side, String dessert, String drink) {
        this.appetizer = appetizer;
        this.mainDish = mainDish;
        this.side = side;
        this.dessert = dessert;
        this.drink = drink;
    }

    public String getAppetizer() {
        if (appetizer.equals("")) return "NONE";
        return appetizer;
    }

    public void setAppetizer(String appetizer) {
        this.appetizer = appetizer;
    }

    public String getMainDish() {
        if (mainDish.equals("")) return "NONE";
        return mainDish;
    }

    public void setMainDish(String mainDish) {
        this.mainDish = mainDish;
    }

    public String getSide() {
        if (side.equals("")) return "NONE";
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getDessert() {
        if (dessert.equals("")) return "NONE";
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getDrink() {
        if (drink.equals("")) return "NONE";
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}
