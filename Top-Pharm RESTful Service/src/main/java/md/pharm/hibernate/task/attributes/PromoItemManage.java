package md.pharm.hibernate.task.attributes;

import md.pharm.hibernate.doctor.attributes.Speciality;
import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Andrei on 12/20/2015.
 */
public class PromoItemManage {
    private Session session;
    Country country;

    public PromoItemManage(String country){
        this.country = Country.valueOf(country);
    }

    public List<PromoItem> getAll(){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<PromoItem> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.task.attributes.PromoItem").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer add(PromoItem speciality){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        Integer doctorID = null;
        try{
            tx = session.beginTransaction();
            doctorID = (Integer) session.save(speciality);
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

    public boolean update(PromoItem speciality){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(speciality);
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

    public boolean delete(PromoItem speciality){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(speciality);
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

    public PromoItem getByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        PromoItem speciality = null;
        try{
            tx = session.beginTransaction();
            speciality = (PromoItem)session.get(PromoItem.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return speciality;
    }
}
