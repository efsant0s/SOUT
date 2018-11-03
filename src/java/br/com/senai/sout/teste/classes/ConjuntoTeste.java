/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.teste.classes;

import br.com.senai.sout.dao.ConjuntoDao;
import br.com.senai.sout.dao.UsuarioDao;
import br.com.senai.sout.model.Conjunto;
import br.com.senai.sout.model.Usuario;

/**
 *
 * @author Aluno
 */
public class ConjuntoTeste {
    public static void main(String[] args) {
        
        cadastraConjuntoPadrao();
        //editaConjuntoPadrao();
        //excluiConjuntoPadrao();
    }
    private static Usuario fazUsuarioPadraoSimples() {
        Usuario user = new Usuario();
        user.setLogin("LoginTeste");
        user.setPassword("senhaUsuario");
        user.setNome("Eduardo Felipe dos Santos");
        user.setRespostaSeguranca("Resposta de segurança");
        user.setTelefone("30374054");
        user.setIePermissao("ROLE_ADMIN");
        return user;
    }
    private static Usuario getUsuarioAleatorio(){
        UsuarioDao dao = new UsuarioDao();
        if(dao.getQuantidadeRegistros() == 0){
            dao.salvar(fazUsuarioPadraoSimples());
        }
        return dao.buscarTodos().get(0);
    }

    private static Conjunto fazConjuntoPadraoSimples() {
       Conjunto conj = new Conjunto();
       conj.setIeTipoBanco("SQL"); 
       conj.setNmTabelaNome("BANCO");
       conj.setIeTipoConjunto("EXP");
       conj.setUsuario(getUsuarioAleatorio());
       return conj;
    }

    private static void cadastraConjuntoPadrao() {
       Conjunto conj = fazConjuntoPadraoSimples();
       ConjuntoDao conDao = new ConjuntoDao();
       conDao.salvar(conj);
    }

    private static void editaConjuntoPadrao() {
        ConjuntoDao dao = new ConjuntoDao();
        if(dao.getQuantidadeRegistros() == 0){
            cadastraConjuntoPadrao();
        }
        Conjunto conj = dao.buscarTodos().get(0);
        conj.setNmTabelaNome("Tabela renomeada");
        dao.salvar(conj);
        
    }

    private static void excluiConjuntoPadrao() {
        ConjuntoDao dao = new ConjuntoDao();
        if(dao.getQuantidadeRegistros() == 0){
            cadastraConjuntoPadrao();
        }
        Conjunto conj = dao.buscarTodos().get(0);
        dao.exclui(conj);
    }

}
