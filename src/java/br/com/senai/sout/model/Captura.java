/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Aluno
 */
@Entity(name = "Captura")
public class Captura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String caminho;
    @ManyToOne() 
    @JoinColumn(name = "ID_CONJUNTO")
    private Conjunto conjuntoOrigem;
    @OneToMany(mappedBy = "capturaTela" , cascade = CascadeType.REMOVE)
    private List<Recorte> recorte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Conjunto getConjuntoOrigem() {
        return conjuntoOrigem;
    }

    public void setConjuntoOrigem(Conjunto conjuntoOrigem) {
        this.conjuntoOrigem = conjuntoOrigem;
    }

    public List<Recorte> getRecorte() {
        return recorte;
    }

    public void setRecorte(List<Recorte> recorte) {
        this.recorte = recorte;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.caminho);
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
        final Captura other = (Captura) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.caminho, other.caminho)) {
            return false;
        }
        if (!Objects.equals(this.recorte, other.recorte)) {
            return false;
        }
        return true;
    }

     
    
}
