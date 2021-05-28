package org.example.entity;


public class Client {

    private long idClient;
    private String fio;
    private String passportNumb;


    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


    public String getPassportNumb() {
        return passportNumb;
    }

    public void setPassportNumb(String passportNumb) {
        this.passportNumb = passportNumb;
    }

}
