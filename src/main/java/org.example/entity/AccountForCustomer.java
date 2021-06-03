package org.example.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class AccountForCustomer implements Serializable {

    private Long idAccountCl;
    private String accountNumber;
    private Long idClient;
    private Long balance;
    private Boolean active;
    private java.sql.Date openDate;

    public AccountForCustomer() {
    }

    public AccountForCustomer(Long idAccountCl, String accountNumber, Long idClient, Long balance, Boolean active, Date openDate) {
        this.idAccountCl = idAccountCl;
        this.accountNumber = accountNumber;
        this.idClient = idClient;
        this.balance = balance;
        this.active = active;
        this.openDate = openDate;
    }

    public long getIdAccountCl() {
        return idAccountCl;
    }

    public void setIdAccountCl(long idAccountCl) {
        this.idAccountCl = idAccountCl;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }


    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountForCustomer that = (AccountForCustomer) o;

        if (!Objects.equals(idAccountCl, that.idAccountCl)) return false;
        if (!Objects.equals(accountNumber, that.accountNumber))
            return false;
        if (!Objects.equals(idClient, that.idClient)) return false;
        if (!Objects.equals(balance, that.balance)) return false;
        if (!Objects.equals(active, that.active)) return false;
        return Objects.equals(openDate, that.openDate);
    }

    @Override
    public int hashCode() {
        int result = idAccountCl != null ? idAccountCl.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (openDate != null ? openDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountForCustomer{" +
                "idAccountCl=" + idAccountCl +
                ", accountNumber='" + accountNumber + '\'' +
                ", idClient=" + idClient +
                ", balance=" + balance +
                ", active=" + active +
                ", openDate=" + openDate +
                '}';
    }
}
