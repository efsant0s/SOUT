/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.dao;

import br.com.senai.sout.model.Captura;
import java.util.List;

/**
 *
 * @author Leonardo.Lima
 */
public class CapturaDao {
    public void salvar(Captura i) {
        if (i.getId() == 0) {
            Repository.inserir(i);
        } else {
          Repository.alterar(i);
        }
        
        Repository.commitar();
    }
    
    public void exclui(Captura i){
        Repository.excluir(i);
        Repository.commitar();
    }
    
    public List<Captura> buscarTodos(){
        return Repository.getEm().createQuery("SELECT U FROM Captura U").getResultList();
    }
    
    public Captura getbyId(Integer id){
        return Repository.getEm().find(Captura.class, id);
    }
    public Long getQuantidadeRegistros(){
        return (Long) Repository.getEm().
                createQuery("select count(u) qtd_registros from Captura u")
                .getSingleResult();
    }
    public List<Captura> buscarTodosByConjunto(int idConjunto){
        return Repository.getEm().
                createQuery("select c from Captura c where c.conjuntoOrigem.id = :idConjunto").setParameter("idConjunto",  + idConjunto).getResultList();
    }  

}
