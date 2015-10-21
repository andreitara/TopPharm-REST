package md.pharm.hibernate.user;

import md.pharm.hibernate.connection.Connection;
import md.pharm.hibernate.connection.ManageConnection;
import md.pharm.hibernate.task.Task;
import md.pharm.restservice.service.util.Country;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Date;

/**
 * Created by Andrei on 9/3/2015.
 */
public class ManageUser {

    private Session session;
    private Country country;

    public ManageUser(String country){
        this.country = Country.valueOf(country);
    }

    public List<User> getUsers(){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<User> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.user.User").list();
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

    public  List<Task> getTasksFromDateToDate(Integer userID, Date start, Date end){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Task> tasks = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Task.class)
                    .add(Restrictions.ge("startDate", start))
                    .add(Restrictions.le("endDate", end))
                    .createCriteria("users")
                    .add(Restrictions.eq("id",userID));
            tasks = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return tasks;
    }

    public  List<Task> getTasksByStatus(Integer userID, String status){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Task> tasks = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Task.class)
                    .add(Restrictions.eq("status", status))
                    .createCriteria("users")
                    .add(Restrictions.eq("id",userID));
            tasks = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return tasks;
    }

    public  List<Task> getTasksByType(Integer userID, String type){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Task> tasks = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Task.class)
                    .add(Restrictions.eq("type", type))
                    .createCriteria("users")
                    .add(Restrictions.eq("id",userID));
            tasks = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return tasks;
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
