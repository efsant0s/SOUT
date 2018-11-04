/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.dao.UsuarioDao;
import br.com.senai.sout.model.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.servlet.ServletException;

/**
 *
 * @author Aluno
 */
@ManagedBean
public class UsuarioView {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private List<Usuario> listaUsuarios = usuarioDao.buscarTodos();
    private Usuario usuario = new Usuario();

    public void fazUsuarioDefault() {
        if (!usuarioDao.isPossuiUsuarioAdmin()) {
            usuarioDao.salvar(new Usuario("123", "123", "123", "ROLE_ADMIN"));
        }
        atualizaLista();
    }

    public void excluiUsuario(Usuario usuario) {
        usuarioDao.excluir(usuario);
        atualizaLista();
    }
    public void salvaUsuario(Usuario usuario) {
        usuarioDao.salvar(usuario);
        atualizaLista();
    }

    public UsuarioView() {
        fazUsuarioDefault();
    }

    private void atualizaLista() {
        listaUsuarios = usuarioDao.buscarTodos();
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void salvaUsuario() throws ServletException{
        
        if(usuarioDao.verificaLoginExistente(usuario.getLogin())){
            throw new ServletException(new Exception("Já existe um usuário de login"));
        }
        usuarioDao.salvar(usuario);
        atualizaLista();
        usuario = new Usuario();
    }

}
