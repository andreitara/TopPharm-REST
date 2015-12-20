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
public class ManageGeneralType {
    private Session session;
    Country country;

    public ManageGeneralType(String country){
        this.country = Country.valueOf(country);
    }

    public List<GeneralType> getAll(){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<GeneralType> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.doctor.attributes.GeneralType").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer add(GeneralType generalType){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        Integer doctorID = null;
        try{
            tx = session.beginTransaction();
            doctorID = (Integer) session.save(generalType);
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

    public boolean update(GeneralType generalType){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(generalType);
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

    public boolean delete(GeneralType generalType){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(generalType);
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

    public GeneralType getByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        GeneralType generalType = null;
        try{
            tx = session.beginTransaction();
            generalType = (GeneralType)session.get(GeneralType.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return generalType;
    }
}
