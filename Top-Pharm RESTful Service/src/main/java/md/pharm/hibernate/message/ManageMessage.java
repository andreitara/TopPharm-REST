package md.pharm.hibernate.message;

import md.pharm.restservice.service.cpc.CPCCustomer;
import md.pharm.util.Country;
import md.pharm.util.DateUtil;
import md.pharm.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Andrei on 9/5/2015.
 */
public class ManageMessage {

    private Session session;
    private Country country;

    public ManageMessage(String country) {
        this.country = Country.valueOf(country);
    }

    public List<CPCCustomer> getAllRepresentatives(Integer userID, String field, boolean ascending){
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<CPCCustomer> list = null;
        try{
            tx = session.beginTransaction();

            String ASC;
            if(ascending) ASC = "ASC";
            else ASC = "DESC";

            Query query = session.createSQLQuery(
                            "select total.id, total.representativeName, isnull(messages.numbers, 0) as messagesNumber, total.hasUnreadMessages\n" +
                            "from\n" +
                            "\t(select u.id, u.user_name as representativeName, u.hasUnreadMessages\n" +
                            "\tfrom [User] as u\n" +
                            "\twhere u.id!=" + userID + ") as total\n" +
                            "left join\n" +
                            "\t(select u.id, u.user_name as representativeName, Count(m.id) as numbers\n" +
                            "\tfrom [User] as u, Message as m\n" +
                            "\twhere ((u.id=m.fromID and " + userID + "=m.toID) or (u.id=m.toID and " + userID + "=m.fromID)) and u.id!=" + userID + " \n" +
                            "\tgroup by u.id, u.user_name) as messages\n" +
                            "on total.id=messages.id\n" +
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

    public List<Message> getMessages() {
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Message> list = null;
        try {
            tx = session.beginTransaction();
            list = session.createQuery("FROM md.pharm.hibernate.message.Message").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public List<Message> getMessagesFromDateToDate(Integer fromID, Integer toID, Date start, Date end) {
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List<Message> list = null;
        try {
            tx = session.beginTransaction();
            list = session.createCriteria(Message.class)
                    .add(Restrictions.le("date", end))
                    .add(Restrictions.ge("date", start))
                    .createAlias("from", "from")
                    .createAlias("to", "to")
                    .add(Restrictions.eq("from.id", fromID))
                    .add(Restrictions.eq("to.id", toID))
                    .addOrder(Order.asc("date"))
                    .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            list = null;
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public List<Message> getMessagesFromDateToDateBidirectional(Integer fromID, Integer toID, Date start, Date end) {
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List list = null;
        try {
            tx = session.beginTransaction();
            Criterion criterion1 = Restrictions.and(Restrictions.eq("from.id", fromID), Restrictions.eq("to.id", toID));
            Criterion criterion2 = Restrictions.and(Restrictions.eq("from.id", toID), Restrictions.eq("to.id", fromID));
            list = session.createCriteria(Message.class)
                    .add(Restrictions.le("date", end))
                    .add(Restrictions.ge("date", start))
                    .createAlias("from", "from")
                    .createAlias("to", "to")
                    .add(Restrictions.or(criterion1, criterion2))
                    .addOrder(Order.asc("date"))
                    .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            list = null;
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public List<Message> getMessagesBidirectional(Integer fromID, Integer toID, Integer firstResult, Integer maxResult) {
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List list = null;
        try {
            tx = session.beginTransaction();
            Criterion criterion1 = Restrictions.and(Restrictions.eq("from.id", fromID), Restrictions.eq("to.id", toID));
            Criterion criterion2 = Restrictions.and(Restrictions.eq("from.id", toID), Restrictions.eq("to.id", fromID));
            list = session.createCriteria(Message.class)
                    .setFirstResult(firstResult)
                    .setMaxResults(maxResult)
                    .createAlias("from", "from")
                    .createAlias("to", "to")
                    .add(Restrictions.or(criterion1, criterion2))
                    .addOrder(Order.desc("date"))
                    .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            list = null;
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public List<Message> getLatestMessagesBidirectional(Integer fromID, Integer toID, Integer maxResult) {
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        List list = null;
        try {
            tx = session.beginTransaction();
            Criterion criterion1 = Restrictions.and(Restrictions.eq("from.id", fromID), Restrictions.eq("to.id", toID));
            Criterion criterion2 = Restrictions.and(Restrictions.eq("from.id", toID), Restrictions.eq("to.id", fromID));
            list = session.createCriteria(Message.class)
                    .setMaxResults(maxResult)
                    .createAlias("from", "from")
                    .createAlias("to", "to")
                    .add(Restrictions.or(criterion1, criterion2))
                    .addOrder(Order.desc("date"))
                    .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            list = null;
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public Integer addMessage(Message message) {
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Integer messageID = null;
        try {
            tx = session.beginTransaction();
            messageID = (Integer) session.save(message);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
        }
        return messageID;
    }

    public int updateMessage(Message message) {
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(message);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
        }
        return message.getId();
    }

    public Message getMessageByID(int id) {
        session = HibernateUtil.getSession(country);
        Transaction tx = null;
        Message message = null;
        try {
            tx = session.beginTransaction();
            message = (Message) session.get(Message.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
        }
        return message;
    }
}
