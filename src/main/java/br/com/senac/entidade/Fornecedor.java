/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.entidade;

import com.sun.istack.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author vitor.kuhnen
 */
@Entity
@Table(name = "fornecedor")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Fornecedor extends Pessoa {

    @Column(nullable = false, length = 18)
    private String cnpj;

    private String inscricao_estadual;

    public Fornecedor() {
    }

    public Fornecedor(String cnpj, String inscricao_estadual, String nome, String email, String telefone) {
        super(nome, email, telefone);
        this.cnpj = cnpj;
        this.inscricao_estadual = inscricao_estadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }
    
    

}
