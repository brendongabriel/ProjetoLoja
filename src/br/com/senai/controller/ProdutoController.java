package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

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
		System.out.println("5) Realizar venda");
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
		System.out.printf("%6s %s \n", "", "---- Produtos Cadastrados ---");
		System.out.printf("| %10s | %8s | %4s | %9s |\n", "Produto", " Preco", "Qtd ", "R$ Total");

		/*
		 * for (ProdutoModel produtoModel : produtos) { System.out.printf("| %10s | %8s
		 * | %4s | %9s
		 * |\n",produtoModel.getNomeDoProduto(),produtoModel.getPrecoDoProduto(),
		 * produtoModel.getQuantidadeDoProduto(),produtoModel.getSaldoEmEstoque()); }
		 */
		produtos.forEach(produto -> {
			System.out.printf("| %10s | %8s | %4s | %9s |\n", produto.getNomeDoProduto(), produto.getPrecoDoProduto(),
					produto.getQuantidadeDoProduto(), produto.getSaldoEmEstoque());
		});
		return produtos;
		/*
		 * for (int i = 0; i < produtos.size(); i++) { System.out.printf("| %10s | %8s |
		 * %4s | %9s
		 * |\n",produtos.get(i).getNomeDoProduto(),produtos.get(i).getPrecoDoProduto(),
		 * produtos.get(i).getQuantidadeDoProduto(),produtos.get(i).getSaldoEmEstoque())
		 * ; }
		 */

	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		int idDoProduto,indexDoCampo;
		ProdutoModel produto = new ProdutoModel();
		System.out.println(" --- EDITAR DADOS DE PRODUTOS ---");
		System.out.println("Informe o ID do produto: ");
		idDoProduto = sc.nextInt();
		
		System.out.println("--- CAMPOS ---"
				+ "\n1) Nome do produto"
				+ "\n2) Preço unítario"
				+ "\n3) Quantidade"
				+ "\nInforme o campo que deseja editar:");
		indexDoCampo = sc.nextInt();
		switch (indexDoCampo) {
		case 1:
			System.out.println("Informe o novo nome do produto: ");
			String nomeProduto = sc.next();
			produto.setNomeDoProduto(nomeProduto);
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			produtos.set(idDoProduto, produto);
			break;
		case 2:
			System.out.println("Informe o novo preço do produto: ");
			double precoProduto = sc.nextDouble();
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(precoProduto);
			produto.setQuantidadeDoProduto(produtos.get(idDoProduto).getQuantidadeDoProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			produtos.set(idDoProduto, produto);
			break;
		case 3:
			System.out.println("Informe a nova quantidade do produto: ");
			int quantidadeProduto = sc.nextInt();
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadeDoProduto(quantidadeProduto);
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			produtos.set(idDoProduto, produto);
			break;
		default:
			System.out.println("Campo inválido");
			break;
		}

		return produto;
	}
}
