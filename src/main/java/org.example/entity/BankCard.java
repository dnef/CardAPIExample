package org.example.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BankCard implements Serializable {

    @JsonProperty("id")
    private long idCard;
    @JsonProperty("cardNumber")
    private String cardNumber;
    @JsonProperty("idAccount")
    private long idAccountClient;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("date_open")
    private java.sql.Date openDate;

    public BankCard() {
    }

    public long getIdCard() {
        return idCard;
    }

    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public long getIdAccountClient() {
        return idAccountClient;
    }

    public void setIdAccountClient(long idAccountClient) {
        this.idAccountClient = idAccountClient;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public java.sql.Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(java.sql.Date openDate) {
        this.openDate = openDate;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", idAccountClient=" + idAccountClient +
                ", active=" + active +
                ", openDate=" + openDate +
                '}';
    }
}
