package br.com.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
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
