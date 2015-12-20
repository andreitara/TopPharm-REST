package md.pharm.hibernate.doctor.attributes;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageSpeciality {
    private Session session;
    Country country;

    public ManageSpeciality(String country){
        this.country = Country.valueOf(country);
    }

    public List<Speciality> getAll(){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Speciality> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.doctor.attributes.Speciality").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer add(Speciality speciality){
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

    public boolean update(Speciality speciality){
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

    public boolean delete(Speciality speciality){
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

    public Speciality getByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Speciality speciality = null;
        try{
            tx = session.beginTransaction();
            speciality = (Speciality)session.get(Speciality.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return speciality;
    }
}
