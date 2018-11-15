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
import java.util.List;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.Part;
import org.primefaces.model.CroppedImage;

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
    private String tipoRecorte;
    private String nomeRecorte;
    private static boolean uploaded;

    public String getNomeRecorte() {
        return nomeRecorte;
    }

    public void setNomeRecorte(String nomeRecorte) {
        this.nomeRecorte = nomeRecorte;
    }

    public String getNewImageName() {
        return newImageName;
    }

    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }

    public String getTipoRecorte() {
        return tipoRecorte;
    }

    public void setTipoRecorte(String tipoRecorte) {
        this.tipoRecorte = tipoRecorte;
    }

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
        MetodosUtils.criaPastasSeNaoExistentes();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.imagemView
                = (ImagemView) FacesContext.getCurrentInstance().getApplication()
                        .getELResolver().getValue(elContext, null, "imagemView");
        if (imagemView.getCaminhoImagem() != null && !imagemView.getCaminhoImagem().isEmpty()) {
            this.caminhoImagem = imagemView.getCaminhoImagem();
            uploaded = true;
        }

    }

    private File criaArquivo(String prefixoNomeArquivo) {
        String path = new File("").getAbsolutePath() + "\\imagens\\";
        int cont = 0;
        File f = new File(path + cont + prefixoNomeArquivo + image.getSubmittedFileName());
        while (f.exists()) {
            cont++;
            f = new File(path + cont + prefixoNomeArquivo + image.getSubmittedFileName());
        }
        this.caminhoImagem = cont + prefixoNomeArquivo + image.getSubmittedFileName();
        return f;
    }

    public void salvaCaptura() throws Exception {
        InputStream in = image.getInputStream();
        File f = criaArquivo("pers-");
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

    }
    private static CroppedImage croppedImage;

    private static String newImageName;

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public void crop() {
        if (croppedImage == null) {
            return;
        }
        uploaded = false;
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(criaArquivo("rec-"));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
            return;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
    }

}
