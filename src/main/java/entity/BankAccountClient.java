package entity;


import java.io.Serializable;

public class BankAccountClient implements Serializable {

  private long idAccountCl;
  private String accountNumber;
  private long idClient;
  private long balance;
  private String active;
  private java.sql.Date openDate;


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
