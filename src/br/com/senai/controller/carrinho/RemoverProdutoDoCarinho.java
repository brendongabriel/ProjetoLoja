package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class RemoverProdutoDoCarinho {

	private Connection connection;

	Scanner entrada = new Scanner(System.in);
	ListaCarrinho listaCarrinho = new ListaCarrinho();

	public RemoverProdutoDoCarinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void RemoverProduto(String cliente) {
		// listarItensNoCarrinho
		PreparedStatement preparedStatement;
		if (listaCarrinho.listarItensNoCarrinho(cliente) == null) {
			return;
		}
		try {
			System.out.println("Insira o ID da venda que você deseja cancelar");
			int idProdutoExcluir = entrada.nextInt();
			String sql2 = "select * from carrinho where codigoVenda = " + idProdutoExcluir;
			preparedStatement = connection.prepareStatement(sql2);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Nao existe venda com esse codigo");
				return;
			}
			// resultSet.previous();
			int codProduto = resultSet.getInt("codigoDoProduto");
			String consulta = "select * from produto where codigoDoProduto = " + codProduto;
			preparedStatement = connection.prepareStatement(consulta);
			ResultSet consultado = preparedStatement.executeQuery();
			consultado.next();
			int qtdeEstoque = consultado.getInt("quantidadeDeProduto");
			int qtdeCarrinho = resultSet.getInt("quantidadeDeItensNoCarrinho");
			int newQtde = qtdeCarrinho + qtdeEstoque;
			double newSaldo = newQtde * resultSet.getDouble("precoDoProduto");
			String atualizar = "UPDATE produto SET quantidadeDeProduto = ? , SaldoEmEstoque = ? WHERE codigoDoProduto = "
					+ codProduto;
			preparedStatement = connection.prepareStatement(atualizar);
			preparedStatement.setInt(1, newQtde);
			preparedStatement.setDouble(2, newSaldo);
			preparedStatement.execute();
			String sql = "delete from carrinho where codigoVenda = " + idProdutoExcluir;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			System.out.println("Produto excluido");

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

}
