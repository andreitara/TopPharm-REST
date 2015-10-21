package md.pharm.hibernate.institution;

import md.TopPharmResTfulServiceApplication;
import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.user.User;
import md.pharm.restservice.service.util.Country;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.Date;
import java.util.List;

/**
 * Created by Andrei on 9/4/2015.
 */
public class ManageInstitution {

    private Session session;
    private Country country;

    public ManageInstitution(String country){
        this.country = Country.valueOf(country);
    }

    public List<Institution> getInstitutions(){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.institution.Institution").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public  List<Institution> getInstitutionsByName(String name){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> institutions = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Institution.class)
                    .add(Restrictions.like("longName", "%" + name + "%"));
            institutions = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institutions;
    }

    public  List<Institution> getInstitutionsByCity( String city){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> institutions = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Institution.class)
                    .createCriteria("address")
                    .add(Restrictions.eq("city",city));
            institutions = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institutions;
    }

    public  List<Institution> getInstitutionsByCityAndDistrict(String city, String district){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> institutions = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Institution.class)
                    .createCriteria("address")
                    .add(Restrictions.eq("city", city))
                    .add(Restrictions.eq("district",district));
            institutions = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institutions;
    }

    public  List<Institution> getInstitutionsByState(String state){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> institutions = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Institution.class)
                    .createCriteria("address")
                    .add(Restrictions.eq("state",state));
            institutions = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institutions;
    }

    public Integer addInstitution(Institution institution){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer institutionID = null;
        try{
            tx = session.beginTransaction();
            institutionID = (Integer) session.save(institution);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institutionID;
    }

    public Integer addInstitutionAddress(Address address){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            id = (Integer) session.save(address);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return id;
    }

    public boolean updateInstitution(Institution institution){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(institution);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public boolean updateAddress(Address address){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(address);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public Institution getInstitutionByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Institution institution = null;
        try{
            tx = session.beginTransaction();
            institution = (Institution)session.get(Institution.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institution;
    }

    public Address getInstitutionAddressByInstitutionID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Institution institution;
        Address address = null;
        boolean flag = true;
        try{
            tx = session.beginTransaction();
            institution = (Institution)session.get(Institution.class, id);
            if(institution!=null) address = institution.getAddress();
            tx.commit();
            flag = false;
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
            flag = true;
        }finally {
        }
        if(flag) return null;
        return address;
    }

    public Institution getInstitutionByLongName(String longName){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Institution institution = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Institution.class);
            institution = (Institution)criteria.add(Restrictions.eq("longName", longName)).uniqueResult();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institution;
    }

    public boolean deleteInstitution(Institution institution){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(institution);
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
