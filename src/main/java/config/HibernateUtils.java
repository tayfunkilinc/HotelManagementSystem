package config;

import domain.Guest;
import domain.Hotel;
import domain.Reservation;
import domain.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure().
                    addAnnotatedClass(Hotel.class).
                    addAnnotatedClass(Room.class).
                    addAnnotatedClass(Guest.class).
                    addAnnotatedClass(Reservation.class);
            sessionFactory=configuration.buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("Initialization of session factory is FAILED!!!");
        }
    }

    //getter
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //SF kapatalim
    public static void shutDown(){
        getSessionFactory().close();
    }

    //sesion kapatma
    public static void closeSesion(Session session){
        if (session != null && session.isOpen()){
            session.close();
        }
    }
}
