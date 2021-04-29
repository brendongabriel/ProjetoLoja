package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.CarrinhoCompra;
import br.com.senai.model.ProdutoModel;

public class CarrinhoController {

	private Scanner sc;

	public CarrinhoController() {
		sc = new Scanner(System.in);
	}

	public void adcionarCarrinho(List<ProdutoModel> produtos, List<CarrinhoCompra> produtosComprados) {
		ProdutoController.listarProdutos(produtos);
		System.out.println("Digite o id do Produto que voce deseja adcionar ao carrinho");
		int idDoProduto = sc.nextInt();
		if (idDoProduto > produtos.size()) {
			System.out.println("Não existe o produto digitado!");
			return;
		}
		idDoProduto--;
		System.out.println("Digite a quantidade a ser adcionada ao carrinho");
		int quantidadeDoProduto = sc.nextInt();
		if (quantidadeDoProduto > produtos.get(idDoProduto).getQuantidadeDoProduto()) {
			System.out.println("Desculpe, mas não temos há quantidade que você deseja");
			return;
		}
		produtos.get(idDoProduto)
				.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto() - quantidadeDoProduto);
		produtos.get(idDoProduto).setSaldoEmEstoque(
				produtos.get(idDoProduto).getQuantidadeDoProduto() * produtos.get(idDoProduto).getPrecoDoProduto());
		CarrinhoCompra carrinhoCompra = new CarrinhoCompra();
		carrinhoCompra.setNomeDoProdutoComprado(produtos.get(idDoProduto).getNomeDoProduto());
		carrinhoCompra.setPrecoDoProdutoComprado(produtos.get(idDoProduto).getPrecoDoProduto());
		carrinhoCompra.setQuantidadeDoProdutoComprado(quantidadeDoProduto);
		carrinhoCompra.setPrecoFinalProdutoComprado(quantidadeDoProduto * carrinhoCompra.getPrecoDoProdutoComprado());
		// return carrinhoCompra;
		produtosComprados.add(carrinhoCompra);
	}

	public List<CarrinhoCompra> listarProdutosCarrinho(List<CarrinhoCompra> produtosComprados) {
		System.out.printf("%8s %s \n", "", "---- Produtos Cadastrados ---");
		System.out.printf("| %2s | %10s | %10s | %4s | %10s |\n", "ID", "Produto", " Preco", "Qtd ", "R$ Total");
		for (int i = 0; i < produtosComprados.size(); i++) {
			System.out.printf("| %2s | %10s | R$%8.2f | %4s | R$%8.2f |\n", i + 1,
					produtosComprados.get(i).getNomeDoProdutoComprado(),
					produtosComprados.get(i).getPrecoDoProdutoComprado(),
					produtosComprados.get(i).getQuantidadeDoProdutoComprado(),
					produtosComprados.get(i).getPrecoFinalProdutoComprado());
		}
		return produtosComprados;
	}

	public void fecharFaturaCarrinho(List<CarrinhoCompra> produtosComprados) {
		float precoTotal = 0;
		for (int i = 0; i < produtosComprados.size(); i++) {
			precoTotal += produtosComprados.get(i).getPrecoFinalProdutoComprado();
		}
		System.out.println("O valor total a pagar é " + precoTotal);
	}
}
