package md.pharm.util;

import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.user.permission.Permission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrei on 10/14/2015.
 */
public class HibernateUtil {

    private static SessionFactory mdFactory;
    private static SessionFactory roFactory;

    public static void initiateHibernate(){
        buildMDSessionFactory();
        buildROSessionFactory();
        createDefaultAdminsIfUsersNotExist();
    }

    public static void buildMDSessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure("mysql/md.hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            mdFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Failed to create MD SessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void buildROSessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure("mysql/ro.hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            roFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Failed to create RO SessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(Country country) {
        switch (country){
            case MD:
                if(mdFactory==null) buildMDSessionFactory();
                return mdFactory;
            case RO:
                if(roFactory==null) buildROSessionFactory();
                return roFactory;
        }
        return null;
    }

    public static Session getSession(Country country){
        SessionFactory factory = getSessionFactory(country);
        Session session = factory.getCurrentSession();
        if(session==null){
            session = factory.openSession();
        }else if(!session.isOpen()){
            session.clear();
            session = factory.openSession();
        }
        return session;
    }

    public static void createDefaultAdminsIfUsersNotExist(){
        ManageUser manageUser = new ManageUser("MD");
        List<User> users = manageUser.getUsers("name", true);
        if(users.size()==0){
            User admin = new User("admin", "admin", "adress", Calendar.getInstance().getTime(),"adminmd", "c93ccd78b2076528346216b3b2f701e6", null, null, null, "MD");
            manageUser.addUser(admin);
        }

        manageUser = new ManageUser("RO");
        users = manageUser.getUsers("name", true);
        if(users.size()==0){
            User admin = new User("admin", "admin", "adress", Calendar.getInstance().getTime(),"adminro", "c93ccd78b2076528346216b3b2f701e6", null, null, null, "RO");
            manageUser.addUser(admin);
        }
    }

}
