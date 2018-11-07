/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.model.Captura;
import br.com.senai.sout.model.Expresso;
import br.com.senai.sout.utils.TesseractUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Aluno
 */
@ManagedBean()
public class ExpressoView implements Serializable {

    private static List<Expresso> lista = new ArrayList<>();

    private Part image;
    private boolean upladed;
    private boolean textoPronto;
    private String textoTraducao;

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

    public List<Expresso> getLista() {
        return lista;
    }

    public void setLista(List<Expresso> lista) {
        this.lista = lista;
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

    public ExpressoView() {
        criaPastasSeNaoExistentes();
    }

    public void criaPastasSeNaoExistentes() {
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
        lista.add(new Expresso(cont, f.getAbsolutePath()));
    }

    public void escreveCampo() throws Exception {
        String textoTotal = "";
        for (Expresso expresso : lista) {
            textoTotal +=  TesseractUtils.retornaStringTraduzida(expresso.getCaminho(), "eng");
        }
        textoTraducao = textoTotal;
        this.textoPronto = true;
    }
}
