package br.com.senai.model;

public class ProdutoModel {

	// Atributos
	private String nomeDoProduto;
	private double precoDoProduto;
	private int quantidadeDoProduto;
	private double saldoEmEstoque;

	// Construtores
	public ProdutoModel() {
	}

	public ProdutoModel(String nomeDoProduto, double precoDoProduto, int quantidadeDoProduto, double saldoEmEstoque) {
		super();
		this.nomeDoProduto = nomeDoProduto;
		this.precoDoProduto = precoDoProduto;
		this.quantidadeDoProduto = quantidadeDoProduto;
		this.saldoEmEstoque = saldoEmEstoque;
	}

	// Metodos
	public String getNomeDoProduto() {
		return nomeDoProduto;
	}

	public void setNomeDoProduto(String nomeDoProduto) {
		this.nomeDoProduto = nomeDoProduto;
	}

	public double getPrecoDoProduto() {
		return precoDoProduto;
	}

	public void setPrecoDoProduto(double precoDoProduto) {
		this.precoDoProduto = precoDoProduto;
	}

	public int getQuantidadeDoProduto() {
		return quantidadeDoProduto;
	}

	public void setQuantidadeDoProduto(int quantidadeDoProduto) {

		this.quantidadeDoProduto = quantidadeDoProduto;
	}

	public double getSaldoEmEstoque() {
		return saldoEmEstoque;
	}

	public void setSaldoEmEstoque(double saldoEmEstoque) {
		this.saldoEmEstoque = saldoEmEstoque;
	}
}
