package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.controller.carrinho.RemoverProdutoDoCarinho;
import br.com.senai.controller.cliente.CadastrarCliente;
import br.com.senai.controller.cliente.ListarCliente;
import br.com.senai.controller.cliente.RemoverCliente;
import br.com.senai.controller.cliente.ValidaCliente;
import br.com.senai.controller.produto.CadastraProduto;
import br.com.senai.controller.produto.DeletaProduto;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {
	public static void main(String[] args) {

		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

		Controller produtoController = new Controller();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		AdicionaItemNoCarrinho adicionaItemNoCarrinho = new AdicionaItemNoCarrinho();
		CadastraProduto cadastraProduto = new CadastraProduto();
		ListaProduto listaProduto = new ListaProduto();
		EditaProduto editaProduto = new EditaProduto();
		DeletaProduto deletaProduto = new DeletaProduto();
		ValidaCliente adicionaCliente = new ValidaCliente();
		RemoverProdutoDoCarinho removerDoCarrinho = new RemoverProdutoDoCarinho();
		ListarCliente listarCliente = new ListarCliente();
		CadastrarCliente cadastrarCliente = new CadastrarCliente();
		RemoverCliente removerCliente = new RemoverCliente();
		Scanner entrada = new Scanner(System.in);

		boolean sair = false;
		boolean encerrar = false;

		do {

			String cliente = adicionaCliente.validadarCliente();
			while (cliente == null) {
				cliente = adicionaCliente.validadarCliente();
			}

			if (cliente.equals("Admin")) {
				do {
					produtoController.menuGerente();
					int opc = produtoController.opcao();

					switch (opc) {
					case 1:
						cadastraProduto.cadastrarProduto();
						break;
					case 2:
						listaProduto.listarProdutos();
						break;
					case 3:
						editaProduto.editarProduto(produtos);
						break;
					case 4:
						deletaProduto.removerProduto();
						break;
					case 5:
						listaCarrinho.listarItensNoCarrinho();
						break;
					case 6:
						listarCliente.listarClientes();
						break;
					case 7:
						cadastrarCliente.cadastrarCliente();
						break;
					case 8:
						removerCliente.deletaCliente();
						break;
					case 9:
						sair = true;
						break;

					default:
						System.out.println("Opção inválida!!!");
						break;
					}
				} while (!sair);
				System.out.println("Voce deseja encerrar o programa? sim - 1/ não - 2");
			} else {

				do {
					produtoController.menu();
					int opc = produtoController.opcao();

					switch (opc) {
					case 1:
						listaProduto.listarProdutos();
						break;
					case 2:
						adicionaItemNoCarrinho.cadastrarItemNoCarrinho(produtos, cliente);
						break;
					case 3:
						listaCarrinho.listarItensNoCarrinho(cliente);
						break;
					case 4:
						listaCarrinho.gerarCupom(cliente);
						break;
					case 5:
						removerDoCarrinho.RemoverProduto(cliente);
						break;
					case 6:
						listaCarrinho.listaProdutosComprados(cliente);
						break;
					case 9:
						sair = true;
						break;

					default:
						System.out.println("Opção inválida!!!");
						break;
					}
				} while (!sair);
				System.out.println("Voce deseja encerrar o programa? sim - 1/ não - 2");
			}
			int opcao = entrada.nextInt();
			if (opcao == 1) {
				encerrar = true;
			}
			sair = false;
		} while (!encerrar);

		System.out.println("Sistema encerrado!!!");
	}
}
