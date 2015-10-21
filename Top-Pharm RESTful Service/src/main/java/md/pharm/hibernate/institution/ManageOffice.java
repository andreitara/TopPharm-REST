package md.pharm.hibernate.institution;

import md.TopPharmResTfulServiceApplication;
import md.pharm.restservice.service.util.Country;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Andrei on 10/5/2015.
 */
public class ManageOffice {

    private Session session;
    private Country country;

    public ManageOffice(String country){
        this.country = Country.valueOf(country);
    }

    public Integer addOffice(Office office){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            id = (Integer) session.save(office);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return id;
    }

    public boolean updateOffice(Office office){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(office);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public Office getOfficeByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Office office = null;
        try{
            tx = session.beginTransaction();
            office = (Office)session.get(Office.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return office;
    }

    public boolean deleteOffice(Office office){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(office);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }
}
