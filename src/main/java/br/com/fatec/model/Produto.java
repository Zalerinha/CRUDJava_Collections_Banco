/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author sorve
 */
public class Produto {
    private int ID_Produto;
    private String nome;
    private double preco;
    private String descricao;
    private boolean receita;
    
    private Fornecedor fornecedor;
    
    
//   Construtor 
    public Produto(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

//   Getters and Setters    
    public int getID_Produto() {
        return ID_Produto;
    }

    public void setID_Produto(int ID_Produto) {
        this.ID_Produto = ID_Produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isReceita() {
        return receita;
    }

    public void setReceita(boolean receita) {
        this.receita = receita;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
// Equals --   HashCODE
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.ID_Produto;
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
        final Produto other = (Produto) obj;
        return this.ID_Produto == other.ID_Produto;
    }

    @Override
    public String toString() {
        return nome;
    }

    
    
    
}
