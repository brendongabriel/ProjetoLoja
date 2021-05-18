package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.CarrinhoModel;

public class ListaCarrinho {

	private Connection connection;

	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void listarItensNoCarrinho(String cliente) {

		PreparedStatement preparedStatement;
		try {
			String sql = "select * from carrinho where cliente = ? and estado = 0 order by codigoDoProduto asc ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cliente);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("\n----- PRODUTOS ESCOLHIDOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");

			if (!resultSet.next()) {
				System.out.println("Não possui dados escolhido");
				return;
			}
			resultSet.previous();
			while (resultSet.next()) {
				System.out.printf("| %2s | %15s | R$%6.2f | %4s | %9.2f |\n", resultSet.getInt("codigoDoProduto"),
						resultSet.getString("nomeDoProduto"), resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeItensNoCarrinho"), resultSet.getDouble("valorTotalPorItem"));
			}

		} catch (Exception e) {
			System.out.println("Errorrrrr");
			return;
		}

	}

	public void listarItensNoCarrinho() {

		PreparedStatement preparedStatement;
		try {
			String sql = "select * from carrinho order by codigoDoProduto asc ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("\n----- PRODUTOS ESCOLHIDOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s | %13s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total",
					"Nome Cliente");

			if (!resultSet.next()) {
				System.out.println("Não possui dados escolhido");
				return;
			}
			resultSet.previous();
			while (resultSet.next()) {
				System.out.printf("| %2s | %15s | R$%6.2f | %4s | %9.2f | %13s |\n", resultSet.getInt("codigoDoProduto"),
						resultSet.getString("nomeDoProduto"), resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeItensNoCarrinho"), resultSet.getDouble("valorTotalPorItem"),
						resultSet.getString("cliente"));
			}

		} catch (Exception e) {
			System.out.println("Errorrrrr");
			return;
		}

	}

	public void gerarCupom(String cliente) {

		PreparedStatement preparedStatement;
		try {
			String sql = "select * from carrinho where cliente = ? and estado = 0";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cliente);
			ResultSet resultSet = preparedStatement.executeQuery();
			double precoFinal = 0;

			if (!resultSet.next()) {
				System.out.println("Voce não comprou nada");
				return;
			}
			resultSet.previous();
			while (resultSet.next()) {
				precoFinal += resultSet.getDouble("valorTotalPorItem");
			}
			this.listarItensNoCarrinho(cliente);
			System.out.println("O valor a ser pago é de " + precoFinal);
			
			String sql2 = "UPDATE carrinho SET estado = 1 WHERE cliente = ?";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, cliente);
			Boolean resultSet2 = preparedStatement.execute();
			System.out.println("Seu carrinho foi limpo\nObrigado pelas compras");

		} catch (Exception e) {
			System.out.println("Errorrrrr");
			return;
		}

	}
}
