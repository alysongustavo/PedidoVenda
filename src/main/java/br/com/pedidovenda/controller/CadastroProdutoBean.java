package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.Categorias;
import br.com.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;
	
	private Produto produto;
	private Categoria categoriaPai;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias; 
	
	public CadastroProdutoBean() {
		produto = new Produto();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotisPostback()) {
			categoriasRaizes = categorias.raizes(); 
		}
	}
	
	public void carregarSubcategoria() {
		subcategorias = categorias.subcategoriasDe(categoriaPai);
	}
	
	public void salvar() {
		System.out.println("Categoria pai selecionada: " + categoriaPai.getDescricao());
		System.out.println("Subcategoria selecionada: " + produto.getCategoria().getDescricao());
	}

	public Produto getProduto() {
		return produto;
	}
	
	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}
	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	
}
