package md.pharm.hibernate.connection;

import md.pharm.restservice.service.util.Country;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Andrei on 9/3/2015.
 */
public class ManageConnection {

    private Session session;
    private Country country;

    public ManageConnection(String country){
        this.country = Country.valueOf(country);
    }

    public ManageConnection(Country country){
        this.country = country;
    }

    public List<Connection> getConnections(){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Connection> list = null;
        try{
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.connection.Connection").list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public Integer addConnection(Connection connection){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer connectionID = null;
        try{
            tx = session.beginTransaction();
            connectionID = (Integer) session.save(connection);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return connectionID;
    }

    public int updateConnection(Connection connection){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(connection);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return connection.getId();
    }

    public boolean deleteConnection(Connection connection){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(connection);
            tx.commit();
            flag=true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public Connection getConnectionByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Connection connection = null;
        try{
            tx = session.beginTransaction();
            connection = (Connection)session.get(Connection.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return connection;
    }

    public Connection getConnectionByConnectionKey(String key){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Connection connection = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Connection.class);
            connection = (Connection) criteria.add(Restrictions.eq("connectionKey", key)).uniqueResult();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return connection;
    }

}
