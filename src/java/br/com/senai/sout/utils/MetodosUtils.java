/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.utils;

import java.io.File;

/**
 *
 * @author Celina
 */
public class MetodosUtils {
    public static void criaPastasSeNaoExistentes() {
        String path = new File("").getAbsolutePath() + "\\imagens";
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
    }
}
