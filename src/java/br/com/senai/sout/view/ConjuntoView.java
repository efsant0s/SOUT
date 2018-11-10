/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.view;

import br.com.senai.sout.dao.ConjuntoDao;
import br.com.senai.sout.dao.UsuarioDao;
import br.com.senai.sout.model.Conjunto;
import br.com.senai.sout.model.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Aluno
 */
@ManagedBean(name = "conjuntoView")
@SessionScoped
public class ConjuntoView implements Serializable {

    private ConjuntoDao conjdao = new ConjuntoDao();
    private Conjunto conjunto = new Conjunto();
    private String caminhoImagem;
    private Part image;
    private boolean upladed;

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

    public ConjuntoDao getConjdao() {
        return conjdao;
    }

    public void setConjdao(ConjuntoDao conjdao) {
        this.conjdao = conjdao;
    }

    public Conjunto getConjunto() {
        return conjunto;
    }

    public void setConjunto(Conjunto conjunto) {
        this.conjunto = conjunto;
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
        

    }

    public void doUpload() throws Exception {
        criaPastasSeNaoExistentes();
        InputStream in = image.getInputStream();

        String path = new File("").getAbsolutePath() + "\\imagens\\conjunto\\";
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
        this.caminhoImagem = path + image.getSubmittedFileName();;
    }

    public void salvaConjunto() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = new UsuarioDao().buscaLogin((String) auth.getCredentials());
        conjunto.setUsuario(usuario);
        conjdao.salvar(conjunto);
    }

}
