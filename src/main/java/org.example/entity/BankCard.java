package org.example.entity;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;

@JsonAutoDetect
public class BankCard implements Serializable {

    @JsonProperty("id")
    private long idCard;
    @JsonProperty("cardNumber")
    private String cardNumber;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("date_open")
    private java.sql.Date openDate;

    public BankCard() {
    }

    public BankCard(long idCard, String cardNumber, String accountNumber, Boolean active, Date openDate) {
        this.idCard = idCard;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.active = active;
        this.openDate = openDate;
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


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
                ", accountNumber=" + accountNumber +
                ", active=" + active +
                ", openDate=" + openDate +
                '}';
    }
}
