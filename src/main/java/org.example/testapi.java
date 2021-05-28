package org.example;

import org.example.service.ServiceCard;

public class testapi {
    public static void main(String[] args) {
       ServiceCard serviceCard = new ServiceCard();
        System.out.println(serviceCard.getBalanceAccountForCard("2635678940023467"));

    }
}
