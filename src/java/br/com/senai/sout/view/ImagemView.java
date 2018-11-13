/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.utils.MetodosUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Celina
 */
@ManagedBean(name = "imagemView")
@SessionScoped
public class ImagemView {

    private String caminhoImagem;
    private Part image;
    private String tipo;
    private static boolean arquivoEnviado;

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ImagemView() {
       MetodosUtils.criaPastasSeNaoExistentes();
    }

    public String setPersonalizado() {
        this.tipo = "PER";
        this.arquivoEnviado = false;
        return "cadastroPersonalizado";
    }

    public String setExpresso() {
        this.tipo = "EXP";
        this.arquivoEnviado = false;
        return "cadastroSuperExpresso";
    }

    public String getTipo() {
        return tipo;
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

    public String getImagemCaminho() {
        return "/images/" + this.caminhoImagem;
    }

    public boolean isArquivoEnviado() {
        return arquivoEnviado;
    }

    public void setArquivoEnviado(boolean upladed) {
        this.arquivoEnviado = upladed;
    }

    

    public void salvaCaptura() throws Exception {
        InputStream in = image.getInputStream();

        String path = new File("").getAbsolutePath() + "\\imagens\\";
        int cont = 0;
        File f = new File(path + cont + "ref-" + image.getSubmittedFileName());
        while (f.exists()) {
            cont++;
            f = new File(path + cont + "ref-" + image.getSubmittedFileName());
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
        arquivoEnviado = true;
        this.caminhoImagem = cont + "ref-" + image.getSubmittedFileName();
    }

}
