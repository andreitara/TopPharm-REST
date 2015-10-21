package md.pharm.hibernate.doctor;

import md.TopPharmResTfulServiceApplication;
import md.pharm.hibernate.institution.Institution;
import md.pharm.restservice.service.util.Country;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageDoctor {
    private Session session;
    Country country;

    public ManageDoctor(String country){
        this.country = Country.valueOf(country);
    }

    public List<Doctor> getDoctors(){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.doctor.Doctor").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public  List<Doctor> getDoctorsBySpeciality(String speciality){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> doctors = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Doctor.class)
                    .add(Restrictions.eq("speciality", speciality));
            doctors = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return doctors;
    }

    public List<Doctor> getDoctorsByInstitutionID(Integer institutionID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> doctors = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Doctor.class)
                    .createCriteria("offices")
                        .add(Restrictions.eq("institutionID", institutionID));
            doctors = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return doctors;
    }

    public  List<Doctor> getDoctorsByPartOfFirstLastAndFatherName(String firstName, String lastName, String fatherName){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> doctors = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Doctor.class)
                    .add(Restrictions.like("firstName", "%" + firstName + "%"))
                    .add(Restrictions.like("lastName", "%" + lastName + "%"))
                    .add(Restrictions.like("fatherName", "%" + fatherName + "%"));
            doctors = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return doctors;
    }

    public  List<Doctor> getDoctorsByPartOfFirstAndLastName(String firstName, String lastName){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> doctors = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Doctor.class)
                    .add(Restrictions.like("firstName", "%" + firstName + "%"))
                    .add(Restrictions.like("lastName", "%" + lastName + "%"));
            doctors = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return doctors;
    }

    public  List<Doctor> getDoctorsByPartOfFirstName(String firstName){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> doctors = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Doctor.class)
                    .add(Restrictions.like("firstName", "%" + firstName + "%"));
            doctors = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return doctors;
    }

    public  List<Doctor> getDoctorsByPartOfLastName(String lastName){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> doctors = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Doctor.class)
                    .add(Restrictions.like("lastName", "%" + lastName + "%"));
            doctors = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return doctors;
    }

    public Integer addDoctor(Doctor doctor){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        Integer doctorID = null;
        try{
            tx = session.beginTransaction();
            doctorID = (Integer) session.save(doctor);
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

    public boolean updateDoctor(Doctor doctor){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(doctor);
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

    public boolean deleteDoctor(Doctor doctor){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(doctor);
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

    public Doctor getDoctorByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Doctor doctor = null;
        try{
            tx = session.beginTransaction();
            doctor = (Doctor)session.get(Doctor.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return doctor;
    }
}
