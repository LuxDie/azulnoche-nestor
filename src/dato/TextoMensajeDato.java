/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dato;

import entidad.TextoMensaje;
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
public class TextoMensajeDato {

    public TextoMensajeDato() {
    }
    
    
    
    public Integer insertarTextoMensaje(TextoMensaje textoMensaje) {
        return save(textoMensaje);
    }
    
    public ArrayList<TextoMensaje> obtenerListaTextoMensajePorIdMensajeIdIdioma(Integer idMensaje, Integer idIdioma) {
        
        
        List resultList = executeHQLQuery(query.concat(" WHERE tm.idMensaje = ").concat(String.valueOf(idMensaje)).concat(" and tm.idIdioma = ").concat(String.valueOf(idIdioma)));
        return (ArrayList<TextoMensaje>) resultList;
    }
    
    private String query = "from TextoMensaje tm";
    
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
