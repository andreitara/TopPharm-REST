package md.pharm.hibernate.doctor;

import md.pharm.hibernate.task.Task;
import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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

    public List<Doctor> getDoctors(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> list = null;
        try{
            tx = session.beginTransaction();

            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Query query = session.createSQLQuery("");

            Criteria criteria = session.createCriteria(Doctor.class)
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

    public  List<Doctor> getDoctorsBySpecialityID(Integer specialityID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> doctors = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);
            Criteria criteria = session.createCriteria(Doctor.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .createCriteria("speciality")
                        .add(Restrictions.eq("id", specialityID));
            doctors = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return doctors;
    }

    public List<Doctor> getDoctorsByInstitutionID(Integer institutionID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> doctors = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);
            Criteria criteria = session.createCriteria(Doctor.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .createCriteria("institution")
                        .add(Restrictions.eq("id", institutionID));
            doctors = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return doctors;
    }

    public  List<Doctor> getDoctorsByPartOfName(String name, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> doctors = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);
            Criteria criteria = session.createCriteria(Doctor.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.like("name", "%" + name + "%"))
                    .addOrder(order);
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
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
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
