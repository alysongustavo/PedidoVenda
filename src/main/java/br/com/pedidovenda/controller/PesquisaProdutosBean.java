package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Produtos;
import br.com.pedidovenda.repository.filter.ProdutoFilter;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;
	
	private List<Produto> produtosFiltrados;
	private ProdutoFilter filtro;
	
	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}
	
	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}
	
}
