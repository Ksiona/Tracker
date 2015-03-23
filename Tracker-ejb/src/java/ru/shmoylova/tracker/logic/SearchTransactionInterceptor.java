package ru.shmoylova.tracker.logic;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import ru.shmoylova.tracker.util.HibernateUtil;

/**
 *
 * @author Ksiona
 */
public class SearchTransactionInterceptor {

    private final static String FIELD_SESSION = "fullTextSession";

    @AroundInvoke
    public Object addLog(InvocationContext context) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            Field prop = context.getTarget().getClass().getDeclaredField(FIELD_SESSION);
            prop.setAccessible(true);
            prop.set(context.getTarget(), fullTextSession);
            tx = fullTextSession.beginTransaction();
            Object rval = context.proceed();
            tx.commit();
            return rval;
        } catch (Exception ex) {
            Logger.getLogger(TransactionInterceptor.class.getName()).log(Level.SEVERE, null, ex);
            if (tx != null) {
                tx.rollback();
            }
            throw new HibernateException(ex.getMessage());
        }
    }
}
