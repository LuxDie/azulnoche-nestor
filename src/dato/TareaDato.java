/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dato;

import entidad.Tarea;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author fabian
 */
public class TareaDato {
    
    public Integer insertarTarea(Tarea tarea) {
        return save(tarea);
    }
    
    public ArrayList<Tarea> obtenerListaTareaPorIdMensaje(Integer idMensaje) {
        List resultList = executeHQLQuery(query.concat(" WHERE t.idMensaje = ").concat(String.valueOf(idMensaje)));
        return (ArrayList<Tarea>) resultList;
    }
    private String query = "from Tarea t";
    
    private List executeHQLQuery(String hql) {
        List resultList = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            resultList = q.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return resultList;
    }
    
    private Integer save(Serializable serializable) {
        Integer newId = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            newId = (int) session.save(serializable);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return newId;
    }
}
