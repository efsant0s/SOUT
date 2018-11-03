/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.dao.ConjuntoDao;
import br.com.senai.sout.model.Captura;
import br.com.senai.sout.model.Conjunto;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

/**
 *
 * @author Aluno
 */
@ManagedBean
public class ConjuntoView {

    
    private static ConjuntoDao conjdao = new ConjuntoDao();
    private static Conjunto conjunto = new Conjunto();

    public static ConjuntoDao getConjdao() {
        return conjdao;
    }

    public static void setConjdao(ConjuntoDao conjdao) {
        ConjuntoView.conjdao = conjdao;
    }

    public static Conjunto getConjunto() {
        return conjunto;
    }

    public static void setConjunto(Conjunto conjunto) {
        ConjuntoView.conjunto = conjunto;
    }
    
    
    

}
