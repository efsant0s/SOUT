package br.com.senai.sout.utils;

import com.asprise.ocr.Ocr;
import java.io.File;
import java.util.ArrayList;

public class ConversorImagemTexto {

    private ArrayList<String> imagens;
    private String diretorio;
    private String idioma;

    public ConversorImagemTexto(ArrayList<String> imagens, String diretorio, String idioma) {
        this.imagens = imagens;
        this.diretorio = diretorio;
        this.idioma = idioma;
    }

    public ConversorImagemTexto(ArrayList<String> imagens, String diretorio) {
        this.imagens = imagens;
        this.diretorio = diretorio;
        this.idioma = "por";
    }

    public ConversorImagemTexto(ArrayList<String> imagens) {
        this.imagens = imagens;
        this.diretorio = new File("").getAbsolutePath() + "\\imagens\\";
        this.idioma = "por";
    }
    

    public ArrayList<String> getImagem() {
        return imagens;
    }

    public void setImagem(ArrayList<String> imagem) {
        this.imagens = imagem;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String retirarTexto(String imagem) {

        
        Ocr ocr = new Ocr(); // create a new OCR engine   
       //Ocr.setUp();
        ocr.startEngine(this.idioma, Ocr.SPEED_FASTEST); // English
        String result = ocr.recognize(new File[]{new File(this.diretorio + imagem)},
        Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT); // PLAINTEXT | XML | PDF | RTF       
        ocr.stopEngine();
        return result;

    }

    public String processarImagem() {
        String resultado = "";

        for (String imagem : imagens) {
            resultado += retirarTexto(imagem) + "\n";
            System.out.println(imagem);
        }
        return resultado;
    }

}
