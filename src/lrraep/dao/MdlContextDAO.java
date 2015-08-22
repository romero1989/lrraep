/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrraep.dao;

import java.util.List;
import javax.persistence.EntityManager;
import lrraep.bean.MdlContext;
import lrraep.utils.GenericDAO;
import lrraep.utils.PersistenceFactory;

/**
 *
 * @author Romero
 */
public class MdlContextDAO extends GenericDAO<MdlContext> {

    public Long recuperarContextID(int CursoID, int NivelContexto) {            
        //select * from mdl_context where instanceid = 2 "ID do Curso" and contextlevel = 50 "Significa que eh sala";
        EntityManager em = PersistenceFactory.createEntityManager();
        Long ContextID = null;
        
        System.out.println("Curso: "+CursoID+" , Contexto: "+NivelContexto);
        
        try {
            List<MdlContext> resultList = em.createQuery(
                    "FROM MdlContext c WHERE c.instanceid = '"+CursoID+"' AND c.contextlevel = '"+NivelContexto+"'")
                    .getResultList();     
            
            MdlContext context = resultList.get(0); //pega o primeiro elemento retornado. basicamente... o unico elemento retornado. pois a consulta que foi realizada acima, é para retornar somente um elemento.
            ContextID = context.getId();
            
        } finally {
            em.close();
        }
        return ContextID;
    }

}
