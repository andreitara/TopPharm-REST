package md.pharm.hibernate.user.attributes;

import md.pharm.hibernate.doctor.attributes.Speciality;
import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageStatus {
    private Session session;
    Country country;

    public ManageStatus(String country){
        this.country = Country.valueOf(country);
    }

    public List<Status> getAll(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Status> list = null;
        try{
            tx = session.beginTransaction();

            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(Status.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order);

            list = criteria.list();

            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer add(Status status){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        Integer doctorID = null;
        try{
            tx = session.beginTransaction();
            doctorID = (Integer) session.save(status);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        if(flag) return doctorID;
        else return null;
    }

    public boolean update(Status status){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(status);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            flag = false;
        }finally {
        }
        return flag;
    }

    public boolean delete(Status status){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(status);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
            flag = false;
        }finally {
        }
        return flag;
    }

    public Status getByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Status speciality = null;
        try{
            tx = session.beginTransaction();
            speciality = (Status)session.get(Status.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return speciality;
    }
}
