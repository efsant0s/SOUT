/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.utils;

import java.io.File;

import net.sourceforge.tess4j.*;
/**
 *
 * @author Celina
 */
public class TesseractUtils {
    public static String retornaStringTraduzida(String caminho, String idioma) throws TesseractException{
        Tesseract instance = Tesseract.getInstance();
        instance.setLanguage(idioma);

        File imageFile = new File(caminho);
        return instance.doOCR(imageFile);
    }
}
