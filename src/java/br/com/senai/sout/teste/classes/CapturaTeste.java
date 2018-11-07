/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.teste.classes;

import br.com.senai.sout.dao.CapturaDao;
import br.com.senai.sout.dao.ConjuntoDao;
import br.com.senai.sout.dao.UsuarioDao;
import br.com.senai.sout.model.Captura;
import br.com.senai.sout.model.Conjunto;
import br.com.senai.sout.model.Usuario;

/**
 *
 * @author Aluno
 */
public class CapturaTeste {
    public static void main(String[] args) {        
        //cadastraCapturaPadrao();
        //editaCapturaPadrao();
        excluiCapturaPadrao();
        //buscaCapturasByConjunto();
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

    private static Captura fazCapturaPadraoSimples() {
       Captura capt = new Captura();
       capt.setCaminho("C://Temp");
      
       if(new ConjuntoDao().getQuantidadeRegistros() == 0){
           cadastraConjuntoPadrao();
       }
       capt.setConjuntoOrigem(new ConjuntoDao().buscarTodos().get(0));       
       return capt;
    }
   
    

    private static void cadastraCapturaPadrao() {
       Captura capt = fazCapturaPadraoSimples();
       CapturaDao conDao = new CapturaDao();
       conDao.salvar(capt);
    }

    private static void editaCapturaPadrao() {
        CapturaDao dao = new CapturaDao();
        if(dao.getQuantidadeRegistros() == 0){
            cadastraCapturaPadrao();
        }
        Captura capt = dao.buscarTodos().get(0);
        capt.setCaminho("CaminhoEditado");
        dao.salvar(capt);
        
    }

    private static void excluiCapturaPadrao() {
        CapturaDao dao = new CapturaDao();
        if(dao.getQuantidadeRegistros() == 0){
            cadastraCapturaPadrao();
        }
        Captura capt = dao.buscarTodos().get(0);
        dao.exclui(capt);
    }

    private static void buscaCapturasByConjunto() {
        CapturaDao dao = new CapturaDao();
        if(dao.getQuantidadeRegistros() == 0){
            cadastraCapturaPadrao();
        }
        ConjuntoDao conjDao = new ConjuntoDao();
        Conjunto conj = conjDao.buscarTodos().get(0);
        for (Captura captura : dao.buscarTodosByConjunto(2)) {
            System.out.println(captura.getCaminho());
        }
    }

}
