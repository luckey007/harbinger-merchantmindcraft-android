package com.example.vijay.merchantminecraft;

/**
 * Created by Vijay on 28/11/2015.
 */
public class CreditCard {
    private int cvv;
    private int monthExpiry;
    private int yearExpiry;
    private String name;
    private int cardNumber;

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getMonthExpiry() {
        return monthExpiry;
    }

    public void setMonthExpiry(int monthExpiry) {
        this.monthExpiry = monthExpiry;
    }

    public int getYearExpiry() {
        return yearExpiry;
    }

    public void setYearExpiry(int yearExpiry) {
        this.yearExpiry = yearExpiry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CreditCard(int cvv, int monthExpiry, int yearExpiry, String name, int cardNumber) {

        this.cvv = cvv;
        this.monthExpiry = monthExpiry;
        this.yearExpiry = yearExpiry;
        this.name = name;
        this.cardNumber = cardNumber;
    }
}
