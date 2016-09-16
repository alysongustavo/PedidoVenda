package br.com.pedidovenda.model;

public enum TipoPessoa {
	
	FISICA("Física"),
	JURIDICA("Juridica");
	
	private String descricao;
	
	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
