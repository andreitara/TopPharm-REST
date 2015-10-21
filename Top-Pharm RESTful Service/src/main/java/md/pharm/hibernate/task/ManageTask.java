package md.pharm.hibernate.task;

import md.TopPharmResTfulServiceApplication;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.restservice.service.util.Country;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageTask {

    private Session session;
    Country country;

    public ManageTask(String country){
        this.country = Country.valueOf(country);
    }

    public List<Task> getTasks(){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Task> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.task.Task").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer addTask(Task task){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer taskID = null;
        try{
            tx = session.beginTransaction();
            taskID = (Integer) session.save(task);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return taskID;
    }

    public boolean updateTask(Task task){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.update(task);
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

    public Task getTaskByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Task task = null;
        try{
            tx = session.beginTransaction();
            task = (Task)session.get(Task.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return task;
    }

    public boolean deleteTask(Task task){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(task);
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

    public boolean deleteDoctorTask(Integer taskID, Integer doctorID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("delete [TopPharm].[dbo].[DoctorTask] where taskID = " + taskID + " and doctorID = " + doctorID);
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

    public boolean deleteProductTask(Integer taskID, Integer productID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete [TopPharm].[dbo].[ProductTask] where taskID = " + taskID + " and productID = " + productID);
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

    public boolean deleteUserTask(Integer taskID, Integer userID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete [TopPharm].[dbo].[UserTask] where taskID = " + taskID + " and userID = " + userID);
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
