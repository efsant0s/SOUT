/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.dao;

/**
 *
 * @author Aluno
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class Repository {
     private static final EntityManagerFactory emf = Persistence.
            createEntityManagerFactory("SOUTPU");
    
    private static final EntityManager em = emf.createEntityManager();

    public static void inserir(Object o) {
        em.persist(o);
    }
    
    public static void alterar(Object o) {
        em.merge(o);
    }
    
    public static void excluir(Object o) {
        em.remove(em.merge(o));
    }
    
    public static void commitar() {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
    
    public static EntityManager getEm() {
        return em;
    }    
}
