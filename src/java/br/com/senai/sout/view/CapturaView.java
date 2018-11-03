/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.dao.CapturaDao;
import br.com.senai.sout.model.Captura;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Aluno
 */
@ManagedBean
public class CapturaView {

    private Captura captura = new Captura();
    private CapturaDao dao = new CapturaDao();
    private List<Captura> capturas = dao.buscarTodos();
    
    
}
