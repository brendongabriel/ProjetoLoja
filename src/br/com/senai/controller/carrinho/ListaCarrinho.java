package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.CarrinhoModel;

public class ListaCarrinho {

	private Connection connection;

	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet listarItensNoCarrinho(String cliente) {

		PreparedStatement preparedStatement;
		try {
			String sql = "select * from carrinho where cliente = ? and estado = 0 order by codigoDoProduto asc ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cliente);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("\n----- PRODUTOS ESCOLHIDOS -----\n");
			System.out.printf("| %9s | %2s | %12s | %8s | %4s | %9s |\n", "Cod Venda", "ID", "Produto", "Pre?o", "Qtd",
					"R$ Total");

			if (!resultSet.next()) {
				System.out.println("N?o possui dados escolhido");
				return null;
			}
			resultSet.previous();
			while (resultSet.next()) {
				System.out.printf("| Cod:%5s | %2s | %12s | R$%6.2f | %4s | %9.2f |\n", resultSet.getInt("codigoVenda"),
						resultSet.getInt("codigoDoProduto"), resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"), resultSet.getInt("quantidadeDeItensNoCarrinho"),
						resultSet.getDouble("valorTotalPorItem"));
			}
			return resultSet;

		} catch (Exception e) {
			System.out.println("Errorrrrr");
			return null;
		}

	}

	public void listarItensNoCarrinho() {

		PreparedStatement preparedStatement;
		try {
			String sql = "select * from carrinho order by codigoDoProduto asc ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("\n----- PRODUTOS ESCOLHIDOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s | %13s |\n", "ID", "Produto", "Pre?o", "Qtd", "R$ Total",
					"Nome Cliente");

			if (!resultSet.next()) {
				System.out.println("N?o possui dados escolhido");
				return;
			}
			resultSet.previous();
			while (resultSet.next()) {
				System.out.printf("| %2s | %15s | R$%6.2f | %4s | %9.2f | %13s |\n",
						resultSet.getInt("codigoDoProduto"), resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"), resultSet.getInt("quantidadeDeItensNoCarrinho"),
						resultSet.getDouble("valorTotalPorItem"), resultSet.getString("cliente"));
			}

		} catch (Exception e) {
			System.out.println("Errorrrrr");
			return;
		}

	}

	public void gerarCupom(String cliente) {
		Scanner sc = new Scanner(System.in);
		PreparedStatement preparedStatement;
		try {
			System.out.println("---- NOME DO CLIENTE: "+cliente+" ----");
			String sql = "select * from carrinho where cliente = ? and estado = 0";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cliente);
			ResultSet resultSet = preparedStatement.executeQuery();
			double precoFinal = 0;

			if (!resultSet.next()) {
				System.out.println("Voce n?o comprou nada");
				return;
			}
			resultSet.previous();
			while (resultSet.next()) {
				precoFinal += resultSet.getDouble("valorTotalPorItem");
			}
			this.listarItensNoCarrinho(cliente);
			System.out.println("--------------- O VALOR A SER PAGO ? DE R$" + precoFinal + "---------------");
			System.out.print("Digite 1 para finalizar a compra:");
			int confirma = sc.nextInt();
			if (confirma != 1) {
				return;
			}		
			String sql2 = "UPDATE carrinho SET estado = 1 WHERE cliente = ?";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, cliente);
			preparedStatement.execute();
			System.out.println("Seu carrinho foi limpo, Obrigado pelas compras");

		} catch (Exception e) {
			System.out.println("Errorrrrr");
			return;
		}

	}
	public ResultSet listaProdutosComprados(String cliente) {
		PreparedStatement preparedStatement;
		try {
			System.out.println("---- NOME DO CLIENTE: "+cliente+" ----");
			String sql = "select * from carrinho where cliente = ? and estado = 1 order by codigoDoProduto asc ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cliente);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("\n----- PRODUTOS ESCOLHIDOS -----\n");
			System.out.printf("| %9s | %2s | %12s | %8s | %4s | %9s |\n", "Cod Venda", "ID", "Produto", "Pre?o", "Qtd",
					"R$ Total");

			if (!resultSet.next()) {
				System.out.println("N?o possui dados escolhido");
				return null;
			}
			resultSet.previous();
			while (resultSet.next()) {
				System.out.printf("| Cod:%5s | %2s | %12s | R$%6.2f | %4s | %9.2f |\n", resultSet.getInt("codigoVenda"),
						resultSet.getInt("codigoDoProduto"), resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"), resultSet.getInt("quantidadeDeItensNoCarrinho"),
						resultSet.getDouble("valorTotalPorItem"));
			}
			return resultSet;

		} catch (Exception e) {
			System.out.println("Errorrrrr");
			return null;
		}
	}
}
