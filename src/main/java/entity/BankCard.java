package entity;


import java.io.Serializable;

public class BankCard implements Serializable {

  private long idCard;
  private String cardNumber;
  private long idAccountClient;
  private Boolean active;
  private java.sql.Date openDate;


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

}
