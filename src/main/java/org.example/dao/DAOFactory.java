package org.example.dao;

import org.example.common.GlobalConfig;

public class DAOFactory {
    public static CardDAO getCardDAO() {
        try {
            //Class dao = Class.forName(GlobalConfig.getProperty("dao.class"));
            Class dao = Class.forName(CardDAO.class.getName());
            return (CardDAO)dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static AccountClientDAO getAccountClientDAO() {
        try {
            //Class dao = Class.forName(GlobalConfig.getProperty("dao.class"));
            Class dao = Class.forName(AccountClientDAO.class.getName());
            return (AccountClientDAO) dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
