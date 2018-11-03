/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.sout.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Aluno
 */
@Entity(name = "Usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(length = 30)
    private String login;
    @Column(length = 30)
    private String password;
    @Column
    private String nome;
    @Column
    private String telefone;
    @Column
    private String email;
    @Column
    private String respostaSeguranca;
    @Column(nullable = false)
    private String iePermissao;

    public Usuario() {

    }

    public Usuario(String nomePadrao, String login, String password, String iePermissao) {
        this.nome = nomePadrao;
        this.login = login;
        this.password = password;
        this.iePermissao = iePermissao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRespostaSeguranca() {
        return respostaSeguranca;
    }

    public void setRespostaSeguranca(String respostaSeguranca) {
        this.respostaSeguranca = respostaSeguranca;
    }

    public String getIePermissao() {
        return iePermissao;
    }

    public void setIePermissao(String iePermissao) {
        this.iePermissao = iePermissao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.login);
        hash = 83 * hash + Objects.hashCode(this.password);
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + Objects.hashCode(this.telefone);
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Objects.hashCode(this.respostaSeguranca);
        hash = 83 * hash + Objects.hashCode(this.iePermissao);
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.respostaSeguranca, other.respostaSeguranca)) {
            return false;
        }
        if (!Objects.equals(this.iePermissao, other.iePermissao)) {
            return false;
        }
        return true;
    }

}
