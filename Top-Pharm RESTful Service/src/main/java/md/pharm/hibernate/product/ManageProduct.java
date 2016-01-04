package md.pharm.hibernate.product;

import md.pharm.hibernate.institution.Institution;
import md.pharm.util.Country;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 10/4/2015.
 */
public class ManageProduct {

    private Session session;
    private Country country;

    public ManageProduct(String country){
        this.country = Country.valueOf(country);
    }

    public List<Product> getProducts(String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Product> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(Product.class)
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

    public List<Product> getProductsByPriority(String priority, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Product> list = null;
        try{
            tx = session.beginTransaction();
            Order order = null;
            if(ascending) order = Order.asc(field);
            else order = Order.desc(field);

            Criteria criteria = session.createCriteria(Product.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .setFetchMode("childFiles", FetchMode.SELECT)
                    .add(Restrictions.eq("priority", priority))
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

    public Integer addProduct(Product product){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            id = (Integer) session.save(product);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return id;
    }
    public Integer addObjective(Objective objective){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            id = (Integer) session.save(objective);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return id;
    }

    public Integer addProductObjective(Objective objective){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer id = null;
        try{
            tx = session.beginTransaction();
            id = (Integer) session.save(objective);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return id;
    }

    public boolean updateProduct(Product product){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public boolean updateObjective(Objective objective){
        session = HibernateUtil.getSession(country);
        boolean flag = false;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(objective);
            tx.commit();
            flag = true;
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return flag;
    }

    public Product getProductByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Product product = null;
        try{
            tx = session.beginTransaction();
            product = (Product)session.get(Product.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return product;
    }

    public Objective getObjectiveByID(int id){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Objective objective = null;
        try{
            tx = session.beginTransaction();
            objective = (Objective)session.get(Objective.class, id);
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return objective;
    }

    public Product getProductByObjectiveID(int objectiveID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Objective objective = null;
        Product product = null;
        try{
            tx = session.beginTransaction();
            objective = (Objective)session.get(Objective.class, objectiveID);
            if(objective != null) product = objective.getProduct();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return product;
    }

    public Set<Objective> getObjectivesByProductID(int productID){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Product product = null;
        Set<Objective> list = null;
        try{
            tx = session.beginTransaction();
            product = (Product)session.get(Product.class, productID);
            //list = product.getObjectives();
            tx.commit();
        }catch (HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public boolean delete(Product product){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(product);
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

    public boolean deleteObjective(Objective objective){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        boolean flag = false;
        try{
            tx = session.beginTransaction();
            session.delete(objective);
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
