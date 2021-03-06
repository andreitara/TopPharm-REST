package md.pharm.hibernate.task;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.product.Product;
import md.pharm.util.Country;
import md.pharm.util.DateUtil;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageTask {

    private Session session;
    Country country;

    public ManageTask(String country){
        this.country = Country.valueOf(country);
    }

    public List<TaskGet> getTasks(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();

            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer","customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
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

    public List<TaskGet> getTasksFromDateToDate(Integer userID, Date start, Date end, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();

            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer","customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.ge("startDate", start))
                    .add(Restrictions.le("endDate", end))
                    .addOrder(order)
                    .createCriteria("users")
                    .add(Restrictions.eq("id", userID));

            list = criteria.list();

            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public List<TaskGet> getTasksByCategory(String category, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer", "customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("category", category))
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

    public List<TaskGet> getTasksBySubmitted(boolean isSubmitted, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer","customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("isSubmitted", isSubmitted))
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

    public List<TaskGet> getTasksByCapital(boolean isCapital, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer","customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("isCapital", isCapital))
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

    public List<TaskGet> getTasksByType(Integer id, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer", "customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("type", "type")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .add(Restrictions.eq("type.id", id));

            list = criteria.list();

            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public List<TaskGet> getTasksByCustomer(Integer id, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer","customer")
                    .createAlias("customer.speciality", "speciality")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    //.createCriteria("customer")
                    .add(Restrictions.eq("customer.id", id));

            list = criteria.list();

            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public List<TaskGet> getTasksByUser(Integer id, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer", "customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("user","user")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .add(Restrictions.eq("user.id", id));

            list = criteria.list();

            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public List<TaskGet> getTasksByUserByCategory(Integer id, String category, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer", "customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("user","user")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("category", category))
                    .addOrder(order)
                    //.createCriteria("user")
                    .add(Restrictions.eq("user.id", id));

            list = criteria.list();

            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public List<TaskGet> getTasksByUserByCapital(Integer id, boolean isCapital, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer","customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("user","user")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("isCapital", isCapital))
                    .addOrder(order)
                    //.createCriteria("user")
                    .add(Restrictions.eq("user.id",id));

            list = criteria.list();

            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public List<TaskGet> getTasksByUserBySubmitted(Integer id, boolean isSubmitted, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer","customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("user","user")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("isSubmitted", isSubmitted))
                    .addOrder(order)
                    //.createCriteria("user")
                    .add(Restrictions.eq("user.id",id));

            list = criteria.list();

            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public List<TaskGet> getTasksByUserByType(Integer userID, Integer typeID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskGet> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;

            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(TaskGet.class)
                    .createAlias("customer", "customer", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("customer.speciality", "speciality", JoinType.LEFT_OUTER_JOIN)
                    .createAlias("user", "user")
                    .createAlias("type", "type")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .add(Restrictions.eq("user.id", userID))
                    .add(Restrictions.eq("type.id", typeID));

            list = criteria.list();

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

    public Integer addTaskHistory(TaskHistory taskHistory){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer taskID = null;
        try{
            tx = session.beginTransaction();
            taskID = (Integer) session.save(taskHistory);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return taskID;
    }

    public Integer addTaskComment(TaskComment taskComment){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer taskID = null;
        try{
            tx = session.beginTransaction();
            taskID = (Integer) session.save(taskComment);
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

    public List<Doctor> getDoctorsByTaskID(int id, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Doctor> taskComments = null;
        try{
            tx = session.beginTransaction();

            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            taskComments = (List<Doctor>) session.createCriteria(Doctor.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .createAlias("tasksAsAttendees", "tasks")
                    .add(Restrictions.eq("tasks.id", id))
                    .list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return taskComments;
    }

    public List<Product> getProductsByTaskID(int id, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Product> taskComments = null;
        try{
            tx = session.beginTransaction();

            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            taskComments = (List<Product>) session.createCriteria(Product.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .addOrder(order)
                    .createAlias("tasks", "tasks")
                    .add(Restrictions.eq("tasks.id", id))
                    .list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return taskComments;
    }

    public List<TaskComment> getTaskCommentByTaskID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskComment> taskComments = null;
        try{
            tx = session.beginTransaction();
            taskComments = (List<TaskComment>) session.createCriteria(TaskComment.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .createCriteria("task")
                    .add(Restrictions.eq("id",id)).list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return taskComments;
    }

    public List<TaskHistory> getTaskHistoryByTaskID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<TaskHistory> taskHistories = null;
        try{
            tx = session.beginTransaction();
            taskHistories = (List<TaskHistory>) session.createCriteria(TaskHistory.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .createCriteria("task")
                    .add(Restrictions.eq("id",id)).list();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return taskHistories;
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

    public boolean deleteTaskComment(TaskComment taskComment){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(taskComment);
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
            Query query = session.createSQLQuery("delete from DoctorTask where taskID = " + taskID + " and doctorID = " + doctorID);
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

    public boolean updateCurrentSubmittedTasks(){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("update Task set isSubmitted = 1 where '" + DateUtil.getStartCurrentDay() + "'<=startDate and endDate<='" + DateUtil.getEndCurrentDay() + "'");
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
            Query query = session.createSQLQuery("delete from ProductTask where taskID = " + taskID + " and productID = " + productID);
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

    public boolean deletePromoItemTask(Integer taskID, Integer promoitemID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete from PromoItemTask where taskID = " + taskID + " and promoitemID = " + promoitemID);
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

    public boolean deleteSampleTask(Integer taskID, Integer sampleID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            //session.delete(task);
            Query query = session.createSQLQuery("delete from SampleTask where taskID = " + taskID + " and sampleID = " + sampleID);
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
