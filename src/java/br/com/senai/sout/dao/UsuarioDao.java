/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.dao;

import br.com.senai.sout.model.Usuario;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class UsuarioDao {

    public void salvar(Usuario u) {
        if (u.getId() == 0) {
            Repository.inserir(u);
        } else {
            Repository.alterar(u);
        }
        Repository.commitar();
    }

    public void excluir(Usuario u) {
        Repository.excluir(u);
        Repository.commitar();
    }

    public List<Usuario> buscarTodos() {
        return Repository.getEm().
                createQuery("select u from Usuario u").getResultList();
    }

    public Usuario buscaId(int id) {
        return Repository.getEm().find(Usuario.class, id);
    }
  
    public Usuario buscaLogin(String login){
        return (Usuario) Repository.getEm().
                createQuery("select u from Usuario u where "
                        + "u.login like :login").setParameter("login", "%" + login + "%")
                .getSingleResult();
    }

    public Usuario buscaNome(String nome) {
        return (Usuario) Repository.getEm().
                createQuery("select u from Usuario u where "
                        + "u.nome like :nome").setParameter("nome", "%" + nome + "%")
                .getSingleResult();
    }
    public Long getQuantidadeRegistros(){
        return (Long) Repository.getEm().
                createQuery("select count(u) qtd_registros from Usuario u")
                .getSingleResult();
    }

    public boolean isPossuiUsuarioAdmin() {
        return ((Long) Repository.getEm().
                createQuery("select count(u) qtd_registros from Usuario u where u.iePermissao = 'ROLE_ADMIN'")
                .getSingleResult() > 0);
    }
}
