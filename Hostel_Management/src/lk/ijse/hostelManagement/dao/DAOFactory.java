package lk.ijse.hostelManagement.dao;

import lk.ijse.hostelManagement.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        RESRVATIONDAO,ROOMDAO,STUDENTDAO,USERDAO,QUERY
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case RESRVATIONDAO:
                return new ReservationDAOImpl();
            case ROOMDAO:
                return new RoomDAOImpl();
            case STUDENTDAO:
                return new StudentDAOImpl();
            case USERDAO:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
        }
        return null;
    }
}
