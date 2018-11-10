/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.dao.CapturaDao;
import br.com.senai.sout.model.Captura;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Aluno
 */
@ManagedBean(name = "capturaView")
@SessionScoped
public class CapturaView  implements Serializable{

    private CapturaDao dao = new CapturaDao();
    private List<Captura> capturas;
    private Part image;
    @ManagedProperty(value = "#{conjuntoView}")
    private ConjuntoView conjuntoView;
    private boolean upladed;

    public ConjuntoView getConjuntoView() {
        return conjuntoView;
    }

    public void setConjuntoView(ConjuntoView conjuntoView) {
        this.conjuntoView = conjuntoView;
    }

    public boolean isUpladed() {
        return upladed;
    }

    public void setUpladed(boolean upladed) {
        this.upladed = upladed;
    }

    public CapturaView() {
    }

    public void atualizaLista() {
        capturas = dao.buscarTodosByConjunto(conjuntoView.getConjunto().getId());
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
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

    public void excluiCaptura(Captura cap) {
        dao.exclui(cap);        
        atualizaLista();
    }



    public void salvaCaptura() throws Exception {
        InputStream in = image.getInputStream();

        String path = new File("").getAbsolutePath() + "\\imagens\\";
        File f = new File(path + image.getSubmittedFileName());
        f.createNewFile();
        FileOutputStream out = new FileOutputStream(f);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        out.close();
        in.close();

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
        upladed = true;
        String caminhoImagem = image.getSubmittedFileName();
        Captura cap = new Captura();
        cap.setCaminho(caminhoImagem);
        cap.setConjuntoOrigem(conjuntoView.getConjunto());
        this.dao.salvar(cap);
        atualizaLista();
    }

}
