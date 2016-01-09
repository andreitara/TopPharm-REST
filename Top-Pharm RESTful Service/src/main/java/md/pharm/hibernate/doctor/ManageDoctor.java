package md.pharm.hibernate.doctor;

import md.pharm.hibernate.task.Task;
import md.pharm.restservice.service.cpc.CPCCustomer;
import md.pharm.util.Country;
import md.pharm.util.DateUtil;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
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

    public List<CPCCustomer> getCPC(Integer userID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<CPCCustomer> list = null;
        try{
            tx = session.beginTransaction();

            String ASC;
            if(ascending) ASC = "ASC";
            else ASC = "DESC";

            Query query = session.createSQLQuery(
                            "select \n" +
                            "total.id, total.name, total.speciality, total.type as targetClass, total.subType as targetSubClass, \n" +
                            "ifnull(total2.tasks, 0) as plannedActivities, \n" +
                            "ifnull(submitted2.tasks, 0) as actualActivities,  \n" +
                            "ifnull(total2.tasks, 0) - ifnull(submitted2.tasks, 0) as callsToMake,\n" +
                            "ifnull(submitted2.tasks, 0)*100/ifnull(total2.tasks, 1) as cpc,\n" +
                            "ifnull(total.tasks, 0) as ydtPlanedActivities, \n" +
                            "ifnull(submitted.tasks, 0) as ydtActualActivities\n" +
                            "\n" +
                            "from\n" +
                            "(select d.id, d.name, d.type, d.subType, s.name as speciality, COUNT(t.id) as tasks\n" +
                            "from Doctor as d, Task as t, User as u, Speciality as s\n" +
                            "where d.id=t.customerID and u.id=t.userID and u.id="+userID+" and s.id=d.specialityID and startDate>='"+ DateUtil.getFirstDayForCurrentYear()+"' and endDate<='"+ DateUtil.getLastDayForCurrentYear() +"'\n" +
                            "group by d.id, d.name, d.type, d.subType, s.name) as total\n" +
                            "\n" +
                            "left join\n" +
                            "(select d.id, d.name, d.type, d.subType, s.name as speciality, COUNT(t.id) as tasks\n" +
                            "from Doctor as d, Task as t, User as u, Speciality as s\n" +
                            "where d.id=t.customerID  and u.id=t.userID and u.id="+userID+" and s.id=d.specialityID and t.isSubmitted=1 and startDate>='"+ DateUtil.getFirstDayForCurrentYear() +"' and endDate<='"+ DateUtil.getLastDayForCurrentYear() +"'\t \n" +
                            "group by d.id, d.name, d.type, d.subType, s.name) as submitted\n" +
                            "on total.id = submitted.id\n" +
                            "\n" +
                            "left join\n" +
                            "(select d.id, d.name, d.type, d.subType, s.name as speciality, COUNT(t.id) as tasks\n" +
                            "from Doctor as d, Task as t, User as u, Speciality as s\n" +
                            "where d.id=t.customerID and u.id=t.userID and u.id="+userID+" and s.id=d.specialityID and startDate>='"+ DateUtil.getFirstDayForCurrentMonth() +"' and endDate<='"+ DateUtil.getLastDayForCurrentMonth() +"'\n" +
                            "group by d.id, d.name, d.type, d.subType, s.name) as total2\n" +
                            "on total.id = total2.id\n" +
                            "\n" +
                            "left join\n" +
                            "(select d.id, d.name, d.type, d.subType, s.name as speciality, COUNT(t.id) as tasks\n" +
                            "from Doctor as d, Task as t, User as u, Speciality as s\n" +
                            "where d.id=t.customerID  and u.id=t.userID and u.id="+userID+" and s.id=d.specialityID and t.isSubmitted=1 and startDate>='"+ DateUtil.getFirstDayForCurrentMonth() +"' and endDate<='"+ DateUtil.getLastDayForCurrentMonth() +"'\t \n" +
                            "group by d.id, d.name, d.type, d.subType, s.name) as submitted2\n" +
                            "on total.id = submitted2.id\n" +
                            "order by " + field + " " + ASC)
                    .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                    //.addEntity(CPCCustomer.class)
                    ;

            list = query.list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public List<CPCCustomer> getUserStatistics(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<CPCCustomer> list = null;
        try{
            tx = session.beginTransaction();

            String ASC;
            if(ascending) ASC = "ASC";
            else ASC = "DESC";

            Query query = session.createSQLQuery(
                            "select \n" +
                            "total.id, total.representativeName, \n" +
                            "ifnull(total2.tasks, 0) as plannedActivities, \n" +
                            "ifnull(submitted2.tasks, 0) as actualActivities,  \n" +
                            "ifnull(total2.tasks, 0) - ifnull(submitted2.tasks, 0) as callsToMake,\n" +
                            "ifnull(submitted2.tasks, 0)*100/ifnull(total2.tasks, 1) as cpc,\n" +
                            "ifnull(total.tasks, 0) as ydtPlanedActivities, \n" +
                            "ifnull(submitted.tasks, 0) as ydtActualActivities\n" +
                            "\n" +
                            "from\n" +
                            "(select u.id, u.user_name as representativeName, COUNT(t.id) as tasks\n" +
                            "from Task as t, User as u \n" +
                            "where u.id=t.userID and startDate>='"+ DateUtil.getFirstDayForCurrentYear()+"' and endDate<='"+ DateUtil.getLastDayForCurrentYear() +"'\n" +
                            "group by u.id, u.user_name) as total\n" +
                            "\n" +
                            "left join\n" +
                            "(select u.id, u.user_name as representativeName, COUNT(t.id) as tasks\n" +
                            "from Task as t, User as u\n" +
                            "where u.id=t.userID and t.isSubmitted=1 and startDate>='"+ DateUtil.getFirstDayForCurrentYear() +"' and endDate<='"+ DateUtil.getLastDayForCurrentYear() +"'\t \n" +
                            "group by u.id, u.user_name) as submitted\n" +
                            "on total.id = submitted.id\n" +
                            "\n" +
                            "left join\n" +
                            "(select u.id, u.user_name as representativeName, COUNT(t.id) as tasks\n" +
                            "from Task as t, User as u\n" +
                            "where u.id=t.userID and startDate>='"+ DateUtil.getFirstDayForCurrentMonth() +"' and endDate<='"+ DateUtil.getLastDayForCurrentMonth() +"'\n" +
                            "group by u.id, u.user_name) as total2\n" +
                            "on total.id = total2.id\n" +
                            "\n" +
                            "left join\n" +
                            "(select u.id, u.user_name as representativeName, COUNT(t.id) as tasks\n" +
                            "from Task as t, User as u\n" +
                            "where u.id=t.userID and t.isSubmitted=1 and startDate>='"+ DateUtil.getFirstDayForCurrentMonth() +"' and endDate<='"+ DateUtil.getLastDayForCurrentMonth() +"'\t \n" +
                            "group by u.id, u.user_name) as submitted2\n" +
                            "on total.id = submitted2.id\n" +
                            "order by " + field + " " + ASC)
                    .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                    //.addEntity(CPCCustomer.class)
                    ;

            list = query.list();
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

    public Object getLatestVisit(Integer userID, Integer doctorID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Object list = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createSQLQuery(
                    "select max(t.startDate) as lastDate\n" +
                            "from Doctor as d, [User] as u, Task as t\n" +
                            "where t.customerID=d.id and t.userID=u.id and u.id="+userID+" and d.id="+doctorID+"\n" +
                            "group by u.id, d.id \n" )
                    //.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                    //.addEntity(CPCCustomer.class)
                    ;

            list = query.uniqueResult();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public boolean deleteInstitutionDoctor(Integer institutionID, Integer doctorID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete InstitutionDoctor where institutionID = " + institutionID + " and doctorID = " + doctorID);
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

    public boolean addInstitutionDoctor(Integer institutionID, Integer doctorID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("insert into InstitutionDoctor(institutionID, doctorID) values ("+institutionID+","+doctorID+")");
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
