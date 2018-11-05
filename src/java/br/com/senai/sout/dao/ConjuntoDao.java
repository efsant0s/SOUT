/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.dao;


import br.com.senai.sout.model.Conjunto;
import java.util.List;

/**
 *
 * @author Leonardo.Lima
 */
public class ConjuntoDao {
    public void salvar(Conjunto i) {
        if (i.getId() == 0) {
            Repository.inserir(i);
        } else {
          Repository.alterar(i);
        }
        
        Repository.commitar();
    }
    
    public void exclui(Conjunto i){
        Repository.excluir(i);
        Repository.commitar();
    }
    
    public List<Conjunto> buscarTodos(){
        return Repository.getEm().createQuery("SELECT U FROM Conjunto U").getResultList();
    }
    
    public Conjunto getbyId(Integer id){
        return Repository.getEm().find(Conjunto.class, id);
    }
    public Long getQuantidadeRegistros(){
        return (Long) Repository.getEm().
                createQuery("select count(u) qtd_registros from Conjunto u")
                .getSingleResult();
    }

    public Conjunto getLastConjuntoByUsuario(String loginUsuario) {
       //TODO Pode dar merda
        return (Conjunto) Repository.getEm().
                createQuery("select u from Conjunto u, Usuario a where u.usuario.id = a.id and a.login = :login").setParameter("nome", loginUsuario)
                .getSingleResult();
   
    }
}
