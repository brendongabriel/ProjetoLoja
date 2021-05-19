package br.com.senai.controller;

import java.util.Scanner;

public class Controller {

	private Scanner entrada;

	public Controller() {
		entrada = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print("> ");
		return entrada.nextInt();
	}

	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Listar produtos");
		System.out.println("2) Adicionar ao carrinho");
		System.out.println("3) Listar itens no carrinho");
		System.out.println("4) Gerar cupom");
		System.out.println("5) Remover produto do Carrinho");
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");
	}

	public void menuGerente() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Hist�rico de Venda");
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");

	}

}
