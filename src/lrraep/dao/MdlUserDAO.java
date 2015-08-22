/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrraep.dao;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import lrraep.bean.MdlUser;
import lrraep.utils.GenericDAO;
import lrraep.utils.PersistenceFactory;

/**
 *
 * @author Romero
 */
public class MdlUserDAO extends GenericDAO<MdlUser> {

    public void printListUsername() {
        Set<MdlUser> list = list();

        if (!list.isEmpty()) {
            for (MdlUser mdlUser : list) {
                System.out.println("" + mdlUser.getFirstname());
            }
        } else {
            System.out.println("Lista Vazia.");
        }
    }
    
    public List<MdlUser> recuperarTodosCadastrosPendentes(){
        EntityManager em = PersistenceFactory.createEntityManager();

        try {
            List<MdlUser> resultList = em.createQuery("from MdlUser where auth='email' AND confirmed='0'").getResultList();
            return resultList;
        } finally {
            em.close();
        }
    }
    
    public int autorizarCadastroPendentes(List<MdlUser> ListaCadastrosPendentes){
        int registrosAtualizados = 0;
        
        for (MdlUser mdlUser : ListaCadastrosPendentes) {
            mdlUser.setConfirmed(true);
            merge(mdlUser);
            registrosAtualizados++;
        }
        
        return registrosAtualizados;
    }

}
