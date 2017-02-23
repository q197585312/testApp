package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;

public class ClearanceBetAmountBean implements Serializable {
    String title;
    int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ClearanceBetAmountBean(int amount, String title) {
        this.amount = amount;
        this.title = title;
    }
}