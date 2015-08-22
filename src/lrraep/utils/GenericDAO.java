/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lrraep.utils;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

/**
 * Classe abstrata responsável por fornecer encapsulamento no acesso aos dados.
 *
 * @author Gert
 * @param <T> Classe Persistente
 * @version v1.23
 * @link: http://fatjava.blogspot.com.br/2013/04/generic-dao-jpa-20.html
 */
public abstract class GenericDAO<T> {

    private Class<T> persistentClass;

    /**
     * Método responsável pela instanciação e extração da classe persistente.
     */
    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Método responsável pela persistência/salvar de uma instância da classe
     * persistente.
     *
     * @param t T
     * @return Boolean
     * @throws EntityExistsException
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     * @throws PersistenceException
     */
    public boolean persist(T t) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        EntityManager em = PersistenceFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            return true;
        } catch (EntityExistsException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (TransactionRequiredException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Método responsável pela atualização/salvar ou atualizar de uma instância
     * da classe persistente.
     *
     * @param t T
     * @return Boolean
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     */
    public boolean merge(T t) throws IllegalArgumentException, TransactionRequiredException {
        EntityManager em = PersistenceFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (TransactionRequiredException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Método responsável pela remoção de uma instância da classe persistente.
     *
     * @param t T
     * @return Boolean
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     */
    public boolean remove(T t) throws IllegalArgumentException, TransactionRequiredException {
        EntityManager em = PersistenceFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (TransactionRequiredException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Método responsável pela busca de uma instância da classe persistente pela
     * chave-primária.
     *
     * @param pk Long
     * @return T
     * @throws IllegalArgumentException
     */
    public T findByPk(Long pk) throws IllegalArgumentException {
        EntityManager em = PersistenceFactory.createEntityManager();

        try {
            return em.find(this.persistentClass, pk);
        } finally {
            em.close();
        }
    }

    /**
     * Método responsável pela busca de uma instância da classe persistente pela
     * chave-primária.
     *
     * @param pk String
     * @return T
     * @throws IllegalArgumentException
     */
    public T find(String pk) throws IllegalArgumentException {
        EntityManager em = PersistenceFactory.createEntityManager();

        try {
            return em.find(this.persistentClass, pk);
        } finally {
            em.close();
        }
    }

    /**
     * Método responsável pela listagem de instâncias da classe persistente.
     *
     * @return List<T>
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public Set<T> list() throws IllegalArgumentException {
        EntityManager em = PersistenceFactory.createEntityManager();

        try {
            System.out.println(""+this.persistentClass.getSimpleName());
            return new HashSet<T>(em.createQuery("from "
                    + this.persistentClass.getSimpleName()).getResultList());
        } finally {
            em.close();
        }
    }
}
