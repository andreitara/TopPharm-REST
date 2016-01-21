package md.pharm.hibernate.task.attributes;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.attributes.GeneralType;
import md.pharm.hibernate.doctor.attributes.Speciality;
import md.pharm.hibernate.task.Task;
import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 12/20/2015.
 */
public class PromoItemManage {
    private Session session;
    Country country;

    public PromoItemManage(String country){
        this.country = Country.valueOf(country);
    }

    public List<PromoItem> getAll(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<PromoItem> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);
            Criteria criteria = session.createCriteria(PromoItem.class)
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

    public Integer add(PromoItem speciality){
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

    public boolean update(PromoItem promoItem){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        Transaction tx2 = null;
        Set<Task> taskSet = null;

        try{
            tx2 = session.beginTransaction();
            PromoItem itemDB = (PromoItem)session.get(PromoItem.class, promoItem.getId());
            taskSet = itemDB.getTasks();
            tx2.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx2!=null)tx2.rollback();
            e.printStackTrace();
            flag = false;
        }

        if(flag) {
            session = HibernateUtil.getSession(country);
            try {
                tx = session.beginTransaction();
                promoItem.setTasks(taskSet);
                session.update(promoItem);
                tx.commit();
                flag = true;
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }

    public boolean delete(PromoItem promoItem){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.createSQLQuery("delete from PromoItemTask where promoitemID="+promoItem.getId()+"").executeUpdate();
            session.delete(promoItem);
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

    public PromoItem getByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        PromoItem speciality = null;
        try{
            tx = session.beginTransaction();
            speciality = (PromoItem)session.get(PromoItem.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return speciality;
    }
}
