package org.example.entity;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;

@JsonAutoDetect
public class BankCard implements Serializable {

    @JsonProperty("id")
    private long idCard;
    @JsonProperty("cardNumber")
    private String cardNumber;
    //@JsonProperty("accountNumberId")
    @JsonIgnore
    private Long accountNumberId;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("date_open")
    private java.sql.Date openDate;

    public BankCard() {
    }

    public BankCard(long idCard, String cardNumber, Long accountNumberId, Boolean active, Date openDate) {
        this.idCard = idCard;
        this.cardNumber = cardNumber;
        this.accountNumberId = accountNumberId;
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

    public Long getAccountNumberId() {
        return accountNumberId;
    }

    public void setAccountNumberId(Long accountNumberId) {
        this.accountNumberId = accountNumberId;
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

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankCard bankCard = (BankCard) o;

        if (idCard != bankCard.idCard) return false;
        if (!cardNumber.equals(bankCard.cardNumber)) return false;
        if (!accountNumberId.equals(bankCard.accountNumberId)) return false;
        if (!active.equals(bankCard.active)) return false;
        return openDate.equals(bankCard.openDate);
    }

    @Override
    public int hashCode() {
        int result = (int) (idCard ^ (idCard >>> 32));
        result = 31 * result + cardNumber.hashCode();
        result = 31 * result + accountNumberId.hashCode();
        result = 31 * result + active.hashCode();
        result = 31 * result + openDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "idCard=" + idCard +
                ", cardNumber='" + cardNumber + '\'' +
                ", accountNumberId=" + accountNumberId +
                ", accountNumber='" + accountNumber + '\'' +
                ", active=" + active +
                ", openDate=" + openDate +
                '}';
    }
}
