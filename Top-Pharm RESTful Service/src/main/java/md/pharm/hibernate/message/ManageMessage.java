package md.pharm.hibernate.message;

import md.pharm.restservice.service.util.Country;
import md.pharm.restservice.service.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
