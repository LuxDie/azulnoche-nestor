/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dato;

import entidad.Comando;
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
public class ComandoDato {
    
    public Integer insertarComando(Comando comando) {
        return save(comando);
    } 
    
    public ArrayList<Comando> obtenerListaComandosDeIdMensaje(Integer idMensaje) {
        List resultList = executeHQLQuery(query.concat(" WHERE c.idMensaje = ").concat(String.valueOf(idMensaje)));
        return (ArrayList<Comando>) resultList;
    }
    
    private String query = "from Comando c";
    
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
