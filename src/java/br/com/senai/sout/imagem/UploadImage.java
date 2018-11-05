/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.imagem;

/**
 *
 * @author Leonardo.Lima
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class UploadImage {

    private Part image;
    private boolean upladed;

    public UploadImage() {
    }

    public UploadImage(Part image) {
        this.image = image;
    }

    public String doUpload() throws Exception {

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
        return path + image.getSubmittedFileName();
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
}
