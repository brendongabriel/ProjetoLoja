package br.com.senai.model;

public class CarrinhoCompra {
	// Atributos
	private String nomeDoProdutoComprado;
	private double precoDoProdutoComprado;
	private int quantidadeDoProdutoComprado;
	private double PrecoFinalProdutoComprado;

	// Construtores
	public CarrinhoCompra() {
	}

	public CarrinhoCompra(String nomeDoProdutoComprado, double precoDoProdutoComprado, int quantidadeDoProdutoComprado,
			double PrecoFinalProdutoComprado) {
		super();
		this.nomeDoProdutoComprado = nomeDoProdutoComprado;
		this.precoDoProdutoComprado = precoDoProdutoComprado;
		this.quantidadeDoProdutoComprado = quantidadeDoProdutoComprado;
		this.PrecoFinalProdutoComprado = PrecoFinalProdutoComprado;
	}

	public String getNomeDoProdutoComprado() {
		return nomeDoProdutoComprado;
	}

	public void setNomeDoProdutoComprado(String nomeDoProdutoComprado) {
		this.nomeDoProdutoComprado = nomeDoProdutoComprado;
	}

	public double getPrecoDoProdutoComprado() {
		return precoDoProdutoComprado;
	}

	public void setPrecoDoProdutoComprado(double precoDoProdutoComprado) {
		this.precoDoProdutoComprado = precoDoProdutoComprado;
	}

	public int getQuantidadeDoProdutoComprado() {
		return quantidadeDoProdutoComprado;
	}

	public void setQuantidadeDoProdutoComprado(int quantidadeDoProdutoComprado) {
		this.quantidadeDoProdutoComprado = quantidadeDoProdutoComprado;
	}

	public double getPrecoFinalProdutoComprado() {
		return PrecoFinalProdutoComprado;
	}

	public void setPrecoFinalProdutoComprado(double precoFinalProdutoComprado) {
		PrecoFinalProdutoComprado = precoFinalProdutoComprado;
	}

}
