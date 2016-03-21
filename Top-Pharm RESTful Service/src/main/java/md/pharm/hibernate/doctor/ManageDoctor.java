package md.pharm.hibernate.doctor;

import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.task.Task;
import md.pharm.restservice.service.cpc.CPCCustomer;
import md.pharm.util.Country;
import md.pharm.util.DateUtil;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageDoctor {
    private Session session;
    Country country;

    public ManageDoctor(String country){
        this.country = Country.valueOf(country);
    }

    public List<Institution> getInstitutionsByDoctorID(Integer doctorID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> list = null;
        try{
            tx = session.beginTransaction();

            //Order order = null;
            //if(ascending) order = Order.asc(field);
            //else order = Order.desc(field);

            Criteria criteria = session.createCriteria(Institution.class)
                    .createAlias("doctors", "doctor")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("doctor.id", doctorID))
                    //.addOrder(order);
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

    public List<Institution> getInstitutionsByUserID(Integer userID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Institution> list = null;
        try{
            tx = session.beginTransaction();

            //Order order = null;
            //if(ascending) order = Order.asc(field);
            //else order = Order.desc(field);

            Criteria criteria = session.createCriteria(Institution.class)
                    .createAlias("users", "user")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("user.id", userID))
                    //.addOrder(order);
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
                    .createAlias("speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
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

    public List<CPCCustomer> getInstitutionsByUserIDWithLastVisitDate(Integer userID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<CPCCustomer> list = null;
        try{
            tx = session.beginTransaction();

            String ASC;
            if(ascending) ASC = "ASC";
            else ASC = "DESC";

            Query query = session.createSQLQuery(
                    "select i.id, i.longName, i.shortName, i.city, i.street, i.phone1, i.phone2, \n" +
                            "\t\ti.institutionTypeID, iType.name as institutionTypeName, lastDate.lastVisitDate\n" +
                            "from Institution as i\n" +
                            "\tinner join InstitutionUser as iu on i.id=iu.institutionID \n" +
                            "\tleft join InstitutionType as iType\n" +
                            "\ton i.institutionTypeID=iType.id\n" +
                            "\tleft join \n" +
                            "\t\t(select i.id, max(t.startDate) as lastVisitDate\n" +
                            "\t\tfrom User as u, Task as t, Institution as i\n" +
                            "\t\twhere i.id = t.institutionID and  t.userID="+userID+" and u.id="+userID+" \n" +
                            "\t\tgroup by u.id, i.id) as lastDate\n" +
                            "\ton i.id=lastDate.id\n" +
                            "where iu.userID="+userID+"\n" +
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

    public List<CPCCustomer> getInstitutionsByUserIDWithLastVisitDateByType(Integer userID, Integer institutionTypeID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<CPCCustomer> list = null;
        try{
            tx = session.beginTransaction();

            String ASC;
            if(ascending) ASC = "ASC";
            else ASC = "DESC";

            Query query = session.createSQLQuery(
                    "select i.id, i.longName, i.shortName, i.city, i.street, i.phone1, i.phone2, \n" +
                            "\t\ti.institutionTypeID, iType.name as institutionTypeName, lastDate.lastVisitDate\n" +
                            "from Institution as i\n" +
                            "\tinner join InstitutionUser as iu on i.id=iu.institutionID \n" +
                            "\tleft join InstitutionType as iType\n" +
                            "\ton i.institutionTypeID=iType.id\n" +
                            "\tleft join \n" +
                            "\t\t(select i.id, max(t.startDate) as lastVisitDate\n" +
                            "\t\tfrom User as u, Task as t, Institution as i\n" +
                            "\t\twhere i.id = t.institutionID and  t.userID="+userID+" and u.id="+userID+" \n" +
                            "\t\tgroup by u.id, i.id) as lastDate\n" +
                            "\ton i.id=lastDate.id\n" +
                            "where iu.userID="+userID+" and i.institutionTypeID="+institutionTypeID+"\n"+
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

    public List<CPCCustomer> getAllDoctorsByUserID(Integer userID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<CPCCustomer> list = null;
        try{
            tx = session.beginTransaction();

            String ASC;
            if(ascending) ASC = "ASC";
            else ASC = "DESC";

            Query query = session.createSQLQuery(
                    "select d.id, d.name, d.email, d.city, d.address, d.birthDate, \n" +
                            "\t\td.officePhone, d.phone1, d.phone2, d.subType, d.type,\n" +
                            "\t\td.specialityID, s.name as specialityName,\n" +
                            "\t\tlastDate.lastVisitDate\n" +
                            "from Doctor as d\n" +
                            "inner join \n" +
                            "    (select id.doctorID from InstitutionDoctor as id\n" +
                            "\tinner join\n" +
                            "\t\t(select institutionID from InstitutionUser where userID="+userID+") as i\n" +
                            "\t\ton id.institutionID=i.institutionID) as userD\n" +
                            "on d.id=userD.doctorID\n" +
                            "left join Speciality as s on s.id=d.specialityID\n" +
                            "left join\n" +
                            "\t\t(select d.id, max(t.startDate) as lastVisitDate\n" +
                            "\t\tfrom User as u, Task as t, Doctor as d\n" +
                            "\t\twhere d.id = t.customerID and  t.userID="+userID+" and u.id="+userID+" \n" +
                            "\t\tgroup by u.id, d.id) as lastDate\n" +
                            "\t\ton lastDate.id=d.id\n" +
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

    public List<CPCCustomer> getAllDoctorsByUserIDandSpecialityID(Integer userID, Integer specialityID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<CPCCustomer> list = null;
        try{
            tx = session.beginTransaction();

            String ASC;
            if(ascending) ASC = "ASC";
            else ASC = "DESC";

            Query query = session.createSQLQuery(
                    "select d.id, d.name, d.email, d.city, d.address, d.birthDate, \n" +
                            "\t\td.officePhone, d.phone1, d.phone2, d.subType, d.type,\n" +
                            "\t\td.specialityID, s.name as specialityName,\n" +
                            "\t\tlastDate.lastVisitDate\n" +
                            "from Doctor as d\n" +
                            "inner join \n" +
                            "    (select id.doctorID from InstitutionDoctor as id\n" +
                            "\tinner join\n" +
                            "\t\t(select institutionID from InstitutionUser where userID="+userID+") as i\n" +
                            "\t\ton id.institutionID=i.institutionID) as userD\n" +
                            "on d.id=userD.doctorID\n" +
                            "left join Speciality as s on s.id=d.specialityID\n" +
                            "left join\n" +
                            "\t\t(select d.id, max(t.startDate) as lastVisitDate\n" +
                            "\t\tfrom User as u, Task as t, Doctor as d\n" +
                            "\t\twhere d.id = t.customerID and  t.userID="+userID+" and u.id="+userID+" \n" +
                            "\t\tgroup by u.id, d.id) as lastDate\n" +
                            "\t\ton lastDate.id=d.id\n" +
                            "\t\twhere specialityID="+specialityID+"\n"+
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
                            "where u.id=t.userID and startDate>='"+ DateUtil.getStartCurrentDay() +"' and endDate<='"+ DateUtil.getEndCurrentDay() +"'\n" +
                            "group by u.id, u.user_name) as total2\n" +
                            "on total.id = total2.id\n" +
                            "\n" +
                            "left join\n" +
                            "(select u.id, u.user_name as representativeName, COUNT(t.id) as tasks\n" +
                            "from Task as t, User as u\n" +
                            "where u.id=t.userID and t.isSubmitted=1 and startDate>='"+ DateUtil.getStartCurrentDay() +"' and endDate<='"+ DateUtil.getEndCurrentDay() +"'\t \n" +
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
                    .createAlias("speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .createCriteria("institutions")
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
                    .createAlias("speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
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
        Transaction tx2 = null;
        Set<Task> taskSet = null;

        try{
            tx2 = session.beginTransaction();
            Doctor doctorDB = (Doctor)session.get(Doctor.class, doctor.getId());
            taskSet = doctorDB.getTasksAsAttendees();
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
                doctor.setTasksAsAttendees(taskSet);
                session.update(doctor);
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

    public boolean deleteDoctor(Doctor doctor){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.createSQLQuery("delete from DoctorTask where doctorID="+doctor.getId()).executeUpdate();
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
                            "from Doctor as d, User as u, Task as t\n" +
                            "where t.customerID=d.id and t.userID=u.id and u.id="+userID+" and d.id="+doctorID+"\n" +
                            "group by u.id, d.id \n" )
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

    public Object getLatestVisitAtInstitution(Integer userID, Integer institutionID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Object list = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createSQLQuery(
                    "select max(t.startDate) as lastDate\n" +
                            "from Institution as i, User as u, Task as t\n" +
                            "where t.institutionID=i.id and t.userID=u.id and u.id="+userID+" and i.id="+institutionID+"\n" +
                            "group by u.id, i.id \n" )
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
            Query query = session.createSQLQuery("delete from InstitutionDoctor where institutionID = " + institutionID + " and doctorID = " + doctorID);
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

    public boolean deleteInstitutionUser(Integer institutionID, Integer userID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete from InstitutionUser where institutionID = " + institutionID + " and userID = " + userID);
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

    public boolean deleteInstitutionDoctorforDoctorID(Integer doctorID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete from InstitutionDoctor where doctorID = " + doctorID);
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

    public boolean addInstitutionUser(Integer institutionID, Integer userID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("insert into InstitutionUser(institutionID, userID) values ("+institutionID+","+userID+")");
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
