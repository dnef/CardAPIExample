package org.example.dao;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

public class DAOFactory {
    private static Log logger = LogFactory.getLog(DAOFactory.class);

    public static CardDAO getCardDAO() {
        try {
            //Class dao = Class.forName(GlobalConfig.getProperty("dao.class"));
            Class dao = Class.forName(CardDAO.class.getName());
            return (CardDAO) dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    public static AccountClientDAO getAccountClientDAO() {
        try {
            Class dao = Class.forName(AccountClientDAO.class.getName());
            return (AccountClientDAO) dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
}
