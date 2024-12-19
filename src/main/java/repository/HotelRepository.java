package repository;

import config.HibernateUtils;
import domain.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.concurrent.ExecutionException;

//Room,Guest ve Reservation için service ve repo classlarını oluşturalım:ÖDEV
public class HotelRepository {

    private Session session;

    //1-b:
    public void save(Hotel hotel){
        try {
            session=HibernateUtils.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();
            session.persist(hotel); //insert into t_hotel values ...
            t.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSesion(session);
        }


    }

}
