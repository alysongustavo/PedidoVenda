package br.com.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CadastroUsuarioBean {
	
	private List<Integer> grupos;
	
	public CadastroUsuarioBean() {
		grupos = new ArrayList<>();
		
		for (int i = 0; i < 2; i++) {
			grupos.add(i);
		}
	}

	public List<Integer> getGrupos() {
		return grupos;
	}

}
