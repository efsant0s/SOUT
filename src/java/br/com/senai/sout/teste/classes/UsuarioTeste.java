/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.teste.classes;

import br.com.senai.sout.dao.UsuarioDao;
import br.com.senai.sout.model.Usuario;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class UsuarioTeste {

    public static void main(String[] args) throws Exception {
        //testeCadastraUsuario();
        //testeEditaUsuario();      
        //testeCadastraListarTodosUsuarios();
        //limpaUsuarios();
        //testeCadastraUsuario();
        //verificaUsuarioAdmin();
        verificaUsuarioExistenteLogin();
        
    }

    private static void testeCadastraUsuario() {
        Usuario edu = fazUsuarioPadraoSimples();
        System.out.println(edu.getId());
        UsuarioDao dao = new UsuarioDao();
        dao.salvar(edu);
        System.out.println(edu.getId());
    }

    private static Usuario fazUsuarioPadraoSimples() {
        Usuario user = new Usuario();
        user.setLogin("123");
        user.setPassword("123");
        user.setNome("Eduardo Felipe dos Santos");
        user.setRespostaSeguranca("Resposta de segurança");
        user.setTelefone("30374054");
        user.setIePermissao("ROLE_ADMIN");
        return user;
    }

    private static void testeCadastraListarTodosUsuarios() {
        List<Usuario> listaUsuario = new UsuarioDao().buscarTodos();
        System.out.println("-----------------Teste de cadastro listagem de usuário-----------------");
        System.out.println("Qtd de usuário em base: " + listaUsuario.size());
        for (int i = 0; i < listaUsuario.size(); i++) {
            System.out.println("ID : " + listaUsuario.get(i).getId() + " -- Nome: " + listaUsuario.get(i).getNome());
        }
        System.out.println("---------------Fim teste de cadastro listagem de usuário---------------");
    }

    private static void limpaUsuarios() {
        System.out.println("-----------------Teste de limpeza de base-----------------");
        UsuarioDao dao = new UsuarioDao();
        for (Usuario usuario : dao.buscarTodos()) {
            dao.excluir(usuario);
            System.out.println("Excluido " + usuario.getNome());
            System.out.println("Qtd de usuário em base: " + dao.getQuantidadeRegistros());
        }

        System.out.println("---------------Fim teste de limpeza de base---------------");
    }

    private static void testeEditaUsuario() throws Exception {
        UsuarioDao userDao = new UsuarioDao();
        if(userDao.getQuantidadeRegistros() == 0L){
             testeCadastraUsuario();
        }
        List<Usuario> lista = userDao.buscarTodos();
        Usuario usuarioEditar = lista.get(0);
        if(usuarioEditar.getId() == 0){
            throw new Exception("Era pra ter um id");
        }
        System.out.println("-----------------Teste de edição de usuário-----------------");
        usuarioEditar.setNome("Jonathan Vieira");
        userDao.salvar(usuarioEditar);
        System.out.println("---------------Fim Teste de edição de usuário---------------");
    }

    private static void verificaUsuarioAdmin() {
        UsuarioDao userDao = new UsuarioDao();
        if(userDao.isPossuiUsuarioAdmin()){
            System.out.println("Possui admin");
        }else{
            System.out.println("Não possui admin");
        }
    }

    private static void verificaUsuarioExistenteLogin() {
         UsuarioDao userDao = new UsuarioDao();
        if(userDao.getQuantidadeRegistros() == 0L){
             testeCadastraUsuario();
        }
        List<Usuario> lista = userDao.buscarTodos();
        Usuario usuarioEditar = lista.get(0);
        System.out.println(userDao.verificaLoginExistente(usuarioEditar.getLogin()));
    }
}
