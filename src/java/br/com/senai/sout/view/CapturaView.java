/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.dao.CapturaDao;
import br.com.senai.sout.dao.ConjuntoDao;
import br.com.senai.sout.model.Captura;
import br.com.senai.sout.model.Conjunto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Aluno
 */
@ManagedBean
public class CapturaView {

    private Captura captura = new Captura();
    private CapturaDao dao = new CapturaDao();
    private List<Captura> capturas = dao.buscarTodosByConjunto(getLastConjuntoByUsuario().getId());
    private Part image;

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public Captura getCaptura() {
        return captura;
    }

    public void setCaptura(Captura captura) {
        this.captura = captura;
    }

    public CapturaDao getDao() {
        return dao;
    }

    public void setDao(CapturaDao dao) {
        this.dao = dao;
    }

    public List<Captura> getCapturas() {
        return capturas;
    }

    public void setCapturas(List<Captura> capturas) {
        this.capturas = capturas;
    }
    
    
    private Conjunto getLastConjuntoByUsuario(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ConjuntoDao conjd = new ConjuntoDao();
        Conjunto conj = conjd.getLastConjuntoByUsuario((String) auth.getCredentials());
        return conj;
    }
    
}
