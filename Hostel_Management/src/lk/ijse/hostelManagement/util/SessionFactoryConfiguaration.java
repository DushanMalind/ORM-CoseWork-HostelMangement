package lk.ijse.hostelManagement.util;

import lk.ijse.hostelManagement.entity.Reservation;
import lk.ijse.hostelManagement.entity.Room;
import lk.ijse.hostelManagement.entity.Student;
import lk.ijse.hostelManagement.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfiguaration {
    private static SessionFactoryConfiguaration factoryConfiguaration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfiguaration(){
        sessionFactory=new Configuration().mergeProperties(Utility.getProperties()).addAnnotatedClass(Student.class)
                .addAnnotatedClass(Reservation.class).addAnnotatedClass(Room.class).addAnnotatedClass(Users.class).buildSessionFactory();
    }

    public static SessionFactoryConfiguaration getInstance(){
        return (null==factoryConfiguaration) ? factoryConfiguaration=new SessionFactoryConfiguaration() : factoryConfiguaration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
