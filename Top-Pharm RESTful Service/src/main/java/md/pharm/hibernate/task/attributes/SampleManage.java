package md.pharm.hibernate.task.attributes;

import md.pharm.hibernate.doctor.attributes.GeneralType;
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
public class SampleManage {
    private Session session;
    Country country;

    public SampleManage(String country){
        this.country = Country.valueOf(country);
    }

    public List<Sample> getAll(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Sample> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);
            Criteria criteria = session.createCriteria(Sample.class)
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

    public Integer add(Sample speciality){
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

    public boolean update(Sample sample){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        Transaction tx2 = null;
        Set<Task> taskSet = null;

        try{
            tx2 = session.beginTransaction();
            Sample sampleDB = (Sample)session.get(Sample.class, sample.getId());
            taskSet = sampleDB.getTasks();
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
                sample.setTasks(taskSet);
                session.update(sample);
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

    public boolean delete(Sample sample){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.createSQLQuery("delete from SampleTask where sampleID="+sample.getId()+"").executeUpdate();
            session.delete(sample);
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

    public Sample getByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Sample speciality = null;
        try{
            tx = session.beginTransaction();
            speciality = (Sample)session.get(Sample.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return speciality;
    }

}
