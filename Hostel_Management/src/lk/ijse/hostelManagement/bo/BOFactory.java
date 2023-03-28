package lk.ijse.hostelManagement.bo;

import lk.ijse.hostelManagement.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hostelManagement.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostelManagement.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostelManagement.bo.custom.impl.UserBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public enum  BOTypes{
        RESERVATIONBO,ROOMBO,STUDENTBO,USERBO
    }
    public SuperBO getBo(BOTypes boTypes){
        switch (boTypes){
            case RESERVATIONBO:
                return new ReservationBOImpl();
            case ROOMBO:
                return new RoomBOImpl();
            case STUDENTBO:
                return new StudentBOImpl();
            case USERBO:
                return new UserBOImpl();
        }
        return null;
    }
}
