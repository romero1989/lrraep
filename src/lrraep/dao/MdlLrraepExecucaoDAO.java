/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrraep.dao;

import java.util.List;
import javax.persistence.EntityManager;
import lrraep.bean.MdlContext;
import lrraep.bean.MdlLrraepExecucao;
import lrraep.utils.GenericDAO;
import lrraep.utils.PersistenceFactory;

/**
 *
 * @author Romero
 */
public class MdlLrraepExecucaoDAO extends GenericDAO<MdlLrraepExecucaoDAO> {
    
    public MdlLrraepExecucao getCodigoExecucao() {
        
        EntityManager em = PersistenceFactory.createEntityManager();
        
        MdlLrraepExecucao execucao = new MdlLrraepExecucao();
        
        try {
            List<MdlLrraepExecucao> resultList = em.createQuery(
                    "FROM MdlLrraepExecucao e WHERE e.concluiu = -1")
                    .getResultList();     
            
            execucao = resultList.get(0);
        } finally {
            em.close();
        }
        
        return execucao;
    }
    
    
}
