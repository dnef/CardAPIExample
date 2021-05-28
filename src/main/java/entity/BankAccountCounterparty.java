package com.sample;


public class BankAccountCounterparty {

  private long idAccountCou;
  private String accountNumber;
  private long idCounterparty;
  private long balance;
  private String active;
  private java.sql.Date openDate;


  public long getIdAccountCou() {
    return idAccountCou;
  }

  public void setIdAccountCou(long idAccountCou) {
    this.idAccountCou = idAccountCou;
  }


  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }


  public long getIdCounterparty() {
    return idCounterparty;
  }

  public void setIdCounterparty(long idCounterparty) {
    this.idCounterparty = idCounterparty;
  }


  public long getBalance() {
    return balance;
  }

  public void setBalance(long balance) {
    this.balance = balance;
  }


  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }


  public java.sql.Date getOpenDate() {
    return openDate;
  }

  public void setOpenDate(java.sql.Date openDate) {
    this.openDate = openDate;
  }

}
