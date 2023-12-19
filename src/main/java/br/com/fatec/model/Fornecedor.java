/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author sorve
 */
public class Fornecedor {
    private int CodFornecedor;
    private String nome;
    private String endereco;
    private String CNPJ;
    private String telefone;

    public Fornecedor() {
    }

    public Fornecedor(int CodFornecedor, String nome) {
        this.CodFornecedor = CodFornecedor;
        this.nome = nome;
    }
    
    
    
    

    public int getCodFornecedor() {
        return CodFornecedor;
    }

    public void setCodFornecedor(int CodFornecedor) {
        this.CodFornecedor = CodFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    

    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
