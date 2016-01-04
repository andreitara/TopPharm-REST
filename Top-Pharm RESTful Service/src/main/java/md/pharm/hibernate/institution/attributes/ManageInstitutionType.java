package md.pharm.hibernate.institution.attributes;

import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.task.Task;
import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Andrei on 9/4/2015.
 */
public class ManageInstitutionType {

    private Session session;
    private Country country;

    public ManageInstitutionType(String country){
        this.country = Country.valueOf(country);
    }

    public List<InstitutionType> getInstitutions(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<InstitutionType> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(InstitutionType.class)
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

    public Integer addInstitution(InstitutionType institution){
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

    public boolean updateInstitution(InstitutionType institution){
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

    public InstitutionType getInstitutionByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        InstitutionType institution = null;
        try{
            tx = session.beginTransaction();
            institution = (InstitutionType)session.get(InstitutionType.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return institution;
    }

    public boolean deleteInstitution(InstitutionType institution){
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
