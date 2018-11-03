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
@Entity(name = "Conjunto")
public class Conjunto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String nmTabelaNome;
    @Column(length = 3)
    private String ieTipoBanco;
    @ManyToOne() 
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;    
    @OneToMany(mappedBy = "conjuntoOrigem" , cascade = CascadeType.REMOVE)
    private List<Captura> capturas;
    @Column(length = 3,  nullable = false)
    private String ieTipoConjunto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmTabelaNome() {
        return nmTabelaNome;
    }

    public void setNmTabelaNome(String nmTabelaNome) {
        this.nmTabelaNome = nmTabelaNome;
    }

    public String getIeTipoBanco() {
        return ieTipoBanco;
    }

    public void setIeTipoBanco(String ieTipoBanco) {
        this.ieTipoBanco = ieTipoBanco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Captura> getCapturas() {
        return capturas;
    }

    public void setCapturas(List<Captura> capturas) {
        this.capturas = capturas;
    }

    public String getIeTipoConjunto() {
        return ieTipoConjunto;
    }

    public void setIeTipoConjunto(String ieTipoConjunto) {
        this.ieTipoConjunto = ieTipoConjunto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.nmTabelaNome);
        hash = 47 * hash + Objects.hashCode(this.ieTipoBanco);
        hash = 47 * hash + Objects.hashCode(this.usuario);
        hash = 47 * hash + Objects.hashCode(this.ieTipoConjunto);
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
        final Conjunto other = (Conjunto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nmTabelaNome, other.nmTabelaNome)) {
            return false;
        }
        if (!Objects.equals(this.ieTipoBanco, other.ieTipoBanco)) {
            return false;
        }
        if (!Objects.equals(this.ieTipoConjunto, other.ieTipoConjunto)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    


}
