/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Celina
 */
@ManagedBean(name = "imagemView")
public class ImagemView {

    private String caminhoImagem;
    private Part image;
    private String tipo;
    private boolean upladed;

    public ImagemView() {
        this.criaPastasSeNaoExistentes();
    }

    public void setPersonalizado() {
        this.tipo = "PER";
    }

    public void setExpresso() {
        this.tipo = "EXP";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public boolean isUpladed() {
        return upladed;
    }

    public void setUpladed(boolean upladed) {
        this.upladed = upladed;
    }

    private void criaPastasSeNaoExistentes() {
        String path = new File("").getAbsolutePath() + "\\imagens";
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        f = new File(path + "\\conjunto");
        if (!f.exists()) {
            f.mkdir();
        }
        f = new File(new File("").getAbsolutePath() + "\\imagens\\recortes");
        if (!f.exists()) {
            f.mkdir();
        }
        f = new File(new File("").getAbsolutePath() + "\\imagens\\super");
        if (!f.exists()) {
            f.mkdir();
        }

    }

    public void salvaCaptura() throws Exception {
        InputStream in = image.getInputStream();

        String path = new File("").getAbsolutePath() + "\\imagens\\super";
        int cont = 0;
        File f = new File(path + cont + image.getSubmittedFileName());
        while (f.exists()) {
            cont++;
            f = new File(path + cont + image.getSubmittedFileName());
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

    }

}
