package repository;

import config.HibernateUtils;
import domain.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
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
    //2-c: DB den id verilen satiri getirme
    public Hotel findById(Long id) {
        try {
            session= HibernateUtils.getSessionFactory().openSession();
            //Transaction t =session.beginTransaction(); -- > fetch islemlerinde transaction'a gerek yok
            return  session.get(Hotel.class, id);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSesion(session);
        }
        return null;
    }
    //3-c: DB den tablonun tum kayitlarini cekme
    public List<Hotel> findAll() {
        try {
            session= HibernateUtils.getSessionFactory().openSession();
            return session.createQuery("FROM Hotel", Hotel.class).getResultList();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSesion(session);
        }
        return null;
    }
}
