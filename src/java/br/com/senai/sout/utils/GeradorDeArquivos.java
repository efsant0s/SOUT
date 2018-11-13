package br.com.senai.sout.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeradorDeArquivos {

    //Caminho que será Salvo o arquivo
    private String diretorio;
    private static int sequencia;
    private String conteudo;
    private String nomeDoArquivo;

    public GeradorDeArquivos(String diretorio, String nomeDoArquivo, String conteudo) {
        this.diretorio = diretorio;
        this.nomeDoArquivo = nomeDoArquivo;
        this.conteudo = conteudo;
    }

    public GeradorDeArquivos(String nomeDoArquivo, String conteudo) {
        this.diretorio = this.diretorio = new File("").getAbsolutePath() + "\\imagens\\";
        this.nomeDoArquivo = nomeDoArquivo;
        this.conteudo = conteudo;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String dir) {
        this.diretorio = dir;
    }

    //Gera um número sequencial para criar os arquivos sem sobrescrever
    private static int gerarSequencia() {
        return sequencia++;
    }

    //Gera um arquivo PDF com o Texto passado por parâmetro
    public void generatePDF() {
        Document document = new Document();

        //Nome do arquivo que será gerado
        nomeDoArquivo += ".pdf";

        // Remove as quebra de Linha
        //conteudo = conteudo.replaceAll("\n", " ");
        try {
            //Cria o arquivo .pdf
            PdfWriter.getInstance(document, new FileOutputStream(diretorio + nomeDoArquivo));

            //Texto do arquivo
            document.open();

            Font fontBold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);

            Paragraph titulo = new Paragraph("Relatório PDF Teste", fontBold);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            Paragraph corpo = new Paragraph(conteudo);
            corpo.setAlignment(Element.AUTHOR);
            document.add(corpo);
        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }

}
