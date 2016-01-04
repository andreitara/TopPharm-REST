package md.pharm.hibernate.institution;

import md.pharm.hibernate.common.Address;
import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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

    public List<Institution> getInstitutions(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> list = null;
        try{
            tx = session.beginTransaction();

            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(Institution.class)
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

    public  List<Institution> getInstitutionsByName(String name, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> institutions = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);
            Criteria criteria = session.createCriteria(Institution.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.like("longName", "%" + name + "%"))
                    .addOrder(order)
                    ;
            institutions = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institutions;
    }

    public  List<Institution> getInstitutionsByCity( String city, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> institutions = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);
            Criteria criteria = session.createCriteria(Institution.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("city", city))
                    .addOrder(order)
                    ;
            institutions = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institutions;
    }

    public  List<Institution> getInstitutionsByType( Integer typeID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> institutions = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);
            Criteria criteria = session.createCriteria(Institution.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .createCriteria("type")
                    .add(Restrictions.eq("id", typeID))
                    ;
            institutions = criteria.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institutions;
    }

    public  List<Institution> getInstitutionsByCityAndType( String city, Integer typeID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> institutions = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);
            Criteria criteria = session.createCriteria(Institution.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("city", city))
                    .addOrder(order)
                    .createCriteria("type")
                    .add(Restrictions.eq("id",typeID))
                    ;
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
