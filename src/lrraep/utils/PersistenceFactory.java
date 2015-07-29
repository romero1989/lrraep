/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lrraep.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
/**
 * Classe responsável por fornecer a referência de um EntityManager.
 * 
 * @author Gert
 * @version v1.23
 * @link: http://fatjava.blogspot.com.br/2013/04/generic-dao-jpa-20.html
 */
public class PersistenceFactory {
        private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("moodle");
        private static EntityManager em;
 
        /**
         * Método estático responsável por fornecer uma referência à instância privada de EntityManager.
         * 
         * @return EntityManager
         */
        public static EntityManager createEntityManager() {
                if(em==null || !em.isOpen()) {
                        return em = emf.createEntityManager();
                } else {
                        return em;
                }
        }
 
        /**
         * Método estático responsável por fechar a instância privada de EntityManager.
         * 
         * @return Boolean
         */
        public static boolean closeEntityManager() {
                try {
                        em.close();
                        return true;
                } catch(Exception e) {
                        return false;
                }
        }
        
        /**
         * Método estático responsável por fechar a instância privada de EntityManagerFactory.
         * 
         * @return Boolean
         */
        public static boolean closeEntityManagerFactory() {
                try {
                        emf.close();
                        return true;
                } catch(Exception e) {
                        return false;
                }
        }
}
