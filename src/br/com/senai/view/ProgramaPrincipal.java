package br.com.senai.view;

import java.util.List;
import java.util.ArrayList;

import br.com.senai.controller.CarrinhoController;
import br.com.senai.controller.ProdutoController;
import br.com.senai.model.CarrinhoCompra;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoCompra> carrinhos = new ArrayList<CarrinhoCompra>();
		ProdutoController produtoController = new ProdutoController();
		CarrinhoController carrinhoController = new CarrinhoController();
		CarrinhoCompra carrinhoCompra = new CarrinhoCompra();

		// Controle do loop de saída
		boolean sair = false;

		do {
			produtoController.menu();
			int opcao = produtoController.opcao();
			switch (opcao) {
			case 1:
				produtos.add(produtoController.cadastrarProduto());
				break;
			case 2:
				produtoController.listarProdutos(produtos);
				break;
			case 3:
				produtoController.editarProduto(produtos);
				break;
			case 4:
				produtoController.removerProduto(produtos);
				break;
			case 5:
				carrinhoController.adcionarCarrinho(produtos, carrinhos);
				break;
			case 6:
				carrinhoController.listarProdutosCarrinho(carrinhos);
				break;
			case 7:
				carrinhoController.fecharFaturaCarrinho(carrinhos);
				break;
			case 9:
				sair = true;				
				break;
			default:
				System.err.println("Opção inválida !!!");
				break;
			}
		} while (!sair);
		System.out.println("Valeu meu rei, tmj");
	}
}
