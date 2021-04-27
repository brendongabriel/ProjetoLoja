package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.CarrinhoCompra;
import br.com.senai.model.ProdutoModel;

public class ProdutoController {

	private Scanner sc;

	public ProdutoController() {
		sc = new Scanner(System.in);
	}

	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Adcionar ao carrinho");
		System.out.println("6) Listar o carrinho");
		System.out.println("9) Sair do sistema");
		System.out.println("---------------------");

	}

	public int opcao() {
		System.out.print("> ");
		return sc.nextInt();
	}

	public ProdutoModel cadastrarProduto() {
		ProdutoModel produtoModel = new ProdutoModel();
		System.out.println("--- Cadastrar item ---");
		System.out.print("Produto: ");
		sc.nextLine();
		produtoModel.setNomeDoProduto(sc.nextLine());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(sc.nextDouble());
		System.out.print("Quantidade: ");
		produtoModel.setQuantidadeDoProduto(sc.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDoProduto() * produtoModel.getPrecoDoProduto());
		return produtoModel;
	}

	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.printf("%8s %s \n", "", "---- Produtos Cadastrados ---");
		System.out.printf("| %2s | %10s | %10s | %4s | %10s |\n", "ID", "Produto", " Preco", "Qtd ", "R$ Total");
		/*
		 * for (ProdutoModel produtoModel : produtos) { System.out.printf("| %10s | %8s
		 * | %4s | %9s
		 * |\n",produtoModel.getNomeDoProduto(),produtoModel.getPrecoDoProduto(),
		 * produtoModel.getQuantidadeDoProduto(),produtoModel.getSaldoEmEstoque()); }
		 */

		// produtos.forEach(produto -> {
		// System.out.printf("| %2s | %10s | R$%6.2s | %4s | %9s
		// |\n",produto.getNomeDoProduto(), produto.getPrecoDoProduto(),
		// produto.getQuantidadeDoProduto(), produto.getSaldoEmEstoque());
		// });
		for (int i = 0; i < produtos.size(); i++) {
			System.out.printf("| %2s | %10s | R$%8.2f | %4s | R$%8.2f |\n", i + 1, produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(), produtos.get(i).getQuantidadeDoProduto(),
					produtos.get(i).getSaldoEmEstoque());
		}
		return produtos;
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {

		int idDoProduto, indexDoCampo;
		ProdutoModel produto = new ProdutoModel();
		if (produtos.size() <= 0) {
			System.out.println("Não existe produto para serem editados");
			return null;
		}
		System.out.println(" --- EDITAR DADOS DE PRODUTOS ---");
		System.out.println("Informe o ID do produto: ");
		idDoProduto = sc.nextInt() - 1;
		if (idDoProduto > produtos.size()) {
			System.out.println("Não existe o produto digitado!");
			return null;
		}
		listarProdutos(produtos);
		System.out.println("--- CAMPOS ---" + "\n1) Nome do produto" + "\n2) Preço unítario" + "\n3) Quantidade"
				+ "\nInforme o campo que deseja editar:");
		indexDoCampo = sc.nextInt();
		switch (indexDoCampo) {
		case 1:
			// Editar o Nome
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			System.out.println("Informe o novo nome do produto: ");
			String nomeProduto = sc.next();
			produto.setNomeDoProduto(nomeProduto);
			produtos.set(idDoProduto, produto);
			break;
		case 2:
			// Editar O preço e o Saldo em estoque
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto());
			System.out.println("Informe o novo preço do produto: ");
			double precoProduto = sc.nextDouble();
			produto.setPrecoDoProduto(precoProduto);
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getQuantidadeDoProduto() * produto.getPrecoDoProduto());
			produtos.set(idDoProduto, produto);
			break;
		case 3:
			// Editar Quantidade e Saldo Em Estoque
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			System.out.println("Informe a nova quantidade do produto: ");
			int quantidadeProduto = sc.nextInt();
			produto.setQuantidadeDoProduto(quantidadeProduto);
			produto.setSaldoEmEstoque(produto.getQuantidadeDoProduto() * produtos.get(idDoProduto).getPrecoDoProduto());
			produtos.set(idDoProduto, produto);
			break;
		default:
			System.err.println("Campo inválido");
			break;
		}

		return produto;
	}

	public void removerProduto(List<ProdutoModel> produtos) {
		if (produtos.size() <= 0) {
			System.out.println("Não existe produtos para serem removidos");
			return;
		}
		listarProdutos(produtos);
		System.out.println("--- Remover Produto ---");
		System.out.println("Digite o id do produto que será removido");
		int idDoProduto = sc.nextInt() - 1;
		if (idDoProduto >= produtos.size()) {
			System.out.println("Esse produto não existe");
			return;
		}
		produtos.remove(idDoProduto);
	}

	public CarrinhoCompra adcionarCarrinho(List<ProdutoModel> produtos, List<CarrinhoCompra> produtosComprados ) {
		listarProdutos(produtos);
		System.out.println("Digite o id do Produto que voce deseja adcionar ao carrinho");
		int idDoProduto = sc.nextInt() - 1;
		System.out.println("Digite a quantidade a ser adcionada ao carrinho");
		int quantidadeDoProduto = sc.nextInt();
		if (quantidadeDoProduto > produtos.get(idDoProduto).getQuantidadeDoProduto()) {
			System.out.println("Desculpe, mas não temos há quantidade que você deseja");
			return null;
		}
		produtos.get(idDoProduto).setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto() - quantidadeDoProduto);
		produtos.get(idDoProduto).setSaldoEmEstoque(produtos.get(idDoProduto).getQuantidadeDoProduto() * produtos.get(idDoProduto).getPrecoDoProduto());
		CarrinhoCompra carrinhoCompra = new CarrinhoCompra();
		carrinhoCompra.setNomeDoProdutoComprado(produtos.get(idDoProduto).getNomeDoProduto());
		carrinhoCompra.setPrecoDoProdutoComprado(produtos.get(idDoProduto).getPrecoDoProduto());
		carrinhoCompra.setQuantidadeDoProdutoComprado(quantidadeDoProduto);
		carrinhoCompra.setPrecoFinalProdutoComprado(quantidadeDoProduto * carrinhoCompra.getPrecoDoProdutoComprado());
		return carrinhoCompra;
	}
	public List<CarrinhoCompra> listarProdutosCarrinho(List<CarrinhoCompra> produtosComprados) {
		System.out.printf("%8s %s \n", "", "---- Produtos Cadastrados ---");
		System.out.printf("| %2s | %10s | %10s | %4s | %10s |\n", "ID", "Produto", " Preco", "Qtd ", "R$ Total");
		for (int i = 0; i < produtosComprados.size(); i++) {
			System.out.printf("| %2s | %10s | R$%8.2f | %4s | R$%8.2f |\n", i + 1, produtosComprados.get(i).getNomeDoProdutoComprado(),
					produtosComprados.get(i).getPrecoDoProdutoComprado(), produtosComprados.get(i).getQuantidadeDoProdutoComprado(),
					produtosComprados.get(i).getPrecoFinalProdutoComprado());
		}
		return produtosComprados;
	}
}
