/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Aluno
 */
@Entity(name = "Recorte")
public class Recorte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Column
    private String textoImagem;
    @Column
    private String dsComandoSQL;
    @ManyToOne(cascade=CascadeType.REMOVE) 
    @JoinColumn(name = "ID_CAPTURA")
    private Captura capturaTela;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextoImagem() {
        return textoImagem;
    }

    public void setTextoImagem(String textoImagem) {
        this.textoImagem = textoImagem;
    }

    public String getDsComandoSQL() {
        return dsComandoSQL;
    }

    public void setDsComandoSQL(String dsComandoSQL) {
        this.dsComandoSQL = dsComandoSQL;
    }

    public Captura getCapturaTela() {
        return capturaTela;
    }

    public void setCapturaTela(Captura capturaTela) {
        this.capturaTela = capturaTela;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.textoImagem);
        hash = 59 * hash + Objects.hashCode(this.dsComandoSQL);
        hash = 59 * hash + Objects.hashCode(this.capturaTela);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recorte other = (Recorte) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.textoImagem, other.textoImagem)) {
            return false;
        }
        if (!Objects.equals(this.dsComandoSQL, other.dsComandoSQL)) {
            return false;
        }
        if (!Objects.equals(this.capturaTela, other.capturaTela)) {
            return false;
        }
        return true;
    }
    
    
    }
