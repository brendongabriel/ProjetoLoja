package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionaItemNoCarrinho {
	
	Scanner entrada = new Scanner(System.in);
	CarrinhoModel carrinhoModel;
	ListaProduto listaProduto;
	EditaProduto editaProduto;
	
	public CarrinhoModel cadastrarItemNoCarrinho(List<ProdutoModel> produtos) {
		carrinhoModel = new CarrinhoModel();
		listaProduto = new ListaProduto();
		editaProduto = new EditaProduto();

		if (produtos.size() <= 0) {
			System.out.println("Não há produtos cadastrados.");
			return null;
		}

		listaProduto.listarProdutos();

		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		carrinhoModel.setIdDoProduto(entrada.nextInt());
		int idDoProduto = carrinhoModel.getIdDoProduto() - 1;

		if (carrinhoModel.getIdDoProduto() > produtos.size()) {
			System.out.println("Este produto não está cadastrado.");
			return null;
		}

		System.out.print("Informe a quantidade desejada: ");
		carrinhoModel.setQuantidadeDeItensNoCarrinho(entrada.nextInt());

		if (carrinhoModel.getQuantidadeDeItensNoCarrinho() > produtos.get(idDoProduto).getQuantidadeDeProduto()) {
			System.out.println("Este produto não possui toda essa quantidade.");
			return null;
		}
		
		editaProduto.atualizarQuantidadeEValorTotal(produtos, carrinhoModel.getQuantidadeDeItensNoCarrinho(), idDoProduto);

		carrinhoModel.setProdutoModel(produtos.get(idDoProduto));
		carrinhoModel.setValorTotalPorItem(
				carrinhoModel.getQuantidadeDeItensNoCarrinho() * produtos.get(idDoProduto).getPrecoDoProduto());

		return carrinhoModel;
	}
}
