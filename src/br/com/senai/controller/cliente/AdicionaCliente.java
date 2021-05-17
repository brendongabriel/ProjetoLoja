package br.com.senai.controller.cliente;

import java.util.Scanner;

public class AdicionaCliente {

	Scanner entrada = new Scanner(System.in);
	
	public String definirCliente() {
		System.out.print("Informe o nome do cliente: ");
		String nome = entrada.next();
		return nome;
	}
}
