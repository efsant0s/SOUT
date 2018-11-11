/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.utils.ConversorImagemTexto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Aluno
 */
@ManagedBean(name = "expressoView")
@SessionScoped
public class ExpressoView {

    private static List<String> lista = new ArrayList<>();
    private ImagemView imagemView;
    private Part image;
    private boolean upladed;
    private boolean textoPronto;
    private String textoTraducao;

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        ExpressoView.lista = lista;
    }

    public ImagemView getImagemView() {
        return imagemView;
    }

    public void setImagemView(ImagemView imagemView) {
        this.imagemView = imagemView;
    }

    public boolean isTextoPronto() {
        return textoPronto;
    }

    public void setTextoPronto(boolean textoPronto) {
        this.textoPronto = textoPronto;
    }

    public String getTextoTraducao() {
        return textoTraducao;
    }

    public void setTextoTraducao(String textoTraducao) {
        this.textoTraducao = textoTraducao;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public boolean isUpladed() {
        return upladed;
    }

    public void setUpladed(boolean upladed) {
        this.upladed = upladed;
    }

    public void remove(String exp) {
        this.lista.remove(exp);
    }

    public String getImagemCaminho(String exp) {
        return "/images/" + exp;
    }

    public ExpressoView() {
        criaPastasSeNaoExistentes();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.imagemView
                = (ImagemView) FacesContext.getCurrentInstance().getApplication()
                        .getELResolver().getValue(elContext, null, "imagemView");
        if (imagemView.getCaminhoImagem() != null && !imagemView.getCaminhoImagem().isEmpty()) {
            lista.add(imagemView.getCaminhoImagem());
        }
    }

    public void criaPastasSeNaoExistentes() {
        String path = new File("").getAbsolutePath() + "\\imagens";
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
    }

    public void salvaCaptura() throws Exception {
        InputStream in = image.getInputStream();

        String path = new File("").getAbsolutePath() + "\\imagens\\";
        int cont = 0;
        File f = new File(path + cont + "exp-" + image.getSubmittedFileName());
        while (f.exists()) {
            cont++;
            f = new File(path + cont + "exp-" + image.getSubmittedFileName());
        }
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
        lista.add(cont + "exp-" + image.getSubmittedFileName());
    }

    public void escreveCampo() throws Exception {
        String textoTotal = "";
        ConversorImagemTexto conversorImagemTexto = new ConversorImagemTexto((ArrayList<String>) lista);
        textoTotal = conversorImagemTexto.processarImagem();
        textoTraducao = textoTotal;
        this.textoPronto = true;
    }
}
