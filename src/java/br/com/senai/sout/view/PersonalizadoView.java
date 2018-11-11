/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.model.Imagem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Eduardo-PC
 */
@ManagedBean(name = "personalizadoView")
public class PersonalizadoView {

    private String caminhoImagem;
    private Part image;
    private ImagemView imagemView;
    private List<String> listaImagem;
    private static boolean uploaded;

    public ImagemView getImagemView() {
        return imagemView;
    }

    public void setImagemView(ImagemView imagemView) {
        this.imagemView = imagemView;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        PersonalizadoView.uploaded = uploaded;
    }

    public String getImagemCaminho() {
        return "/images/" + this.caminhoImagem;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public List<String> getListaImagem() {
        return listaImagem;
    }

    public void setListaImagem(List<String> listaImagem) {
        this.listaImagem = listaImagem;
    }

    public PersonalizadoView() {
        criaPastasSeNaoExistentes();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.imagemView
                = (ImagemView) FacesContext.getCurrentInstance().getApplication()
                        .getELResolver().getValue(elContext, null, "imagemView");
        if (imagemView.getCaminhoImagem() != null && !imagemView.getCaminhoImagem().isEmpty()) {
            listaImagem.add(imagemView.getCaminhoImagem());
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
        File f = new File(path + cont + "pers-" + image.getSubmittedFileName());
        while (f.exists()) {
            cont++;
            f = new File(path + cont + "pers-" + image.getSubmittedFileName());
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
        uploaded = true;
        this.caminhoImagem = cont + "pers-" + image.getSubmittedFileName();
    }

}
