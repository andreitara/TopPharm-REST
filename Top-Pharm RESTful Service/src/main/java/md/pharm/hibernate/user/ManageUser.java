package md.pharm.hibernate.user;

import md.pharm.hibernate.connection.Connection;
import md.pharm.hibernate.connection.ManageConnection;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.user.permission.Permission;
import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * Created by Andrei on 9/3/2015.
 */
public class ManageUser {

    private Session session;
    private Country country;

    public ManageUser(String country){
        this.country = Country.valueOf(country);
    }

    public List<User> getUsers(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<User> list = null;
        try{
            tx = session.beginTransaction();

            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(User.class)
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

    public List<Doctor> getDoctorsFromUser(Integer userID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(Doctor.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .createAlias("users", "users")
                    .add(Restrictions.eq("users.id", userID))
                    ;
            list = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer addUser(User user){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer userID = null;
        try{
            tx = session.beginTransaction();
            userID = (Integer) session.save(user);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return userID;
    }

    public boolean updateUser(User user){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public boolean updateUserAddDoctor(Integer doctorID, Doctor doctor){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            User user = (User)session.get(User.class, doctorID);
            Set<Doctor> doctors = user.getDoctors();
            if(doctors==null) doctors = new HashSet<>();
            doctors.add(doctor);
            user.setDoctors(doctors);
            session.update(user);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public boolean deleteUser(User user){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public User getUserByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        User user = null;
        try{
            tx = session.beginTransaction();
            user = (User)session.get(User.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return user;
    }

    public User getUserByUsername(String username){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        User user = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            user = (User) criteria.add(Restrictions.eq("username",username)).uniqueResult();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return user;
    }

    public User getUserByConnectionKey(String connectionKey){
        session = HibernateUtil.getSession(country);
        User user = null;
        ManageConnection manageConnection = new ManageConnection(country);
        Connection connection = manageConnection.getConnectionByConnectionKey(connectionKey);
        if(connection!=null) {
            user = connection.getUser();
        }
        return user;
    }

    public boolean deleteDoctorUser(Integer userID, Integer doctorID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("delete [TopPharm].[dbo].[UserDoctor] where userID = " + userID + " and doctorID = " + doctorID);
            int result = query.executeUpdate();
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



}
