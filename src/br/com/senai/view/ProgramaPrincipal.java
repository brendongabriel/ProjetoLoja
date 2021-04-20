package br.com.senai.view;

import java.util.List;
import java.util.ArrayList;
import br.com.senai.controller.ProdutoController;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		ProdutoController produtoController = new ProdutoController();

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
				System.out.println(produtoController.listarProdutos(produtos));
				break;
			case 3:
				produtoController.editarProduto(produtos);
				break;
			case 9:
				sair = true;
				break;
			default:
				System.out.println("Opção inválida meu rei!!!");
				break;
			}
		} while (!sair);
		System.out.println("Valeu meu rei, tmj");
	}
}
