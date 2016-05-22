package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.pedidovenda.model.Usuario;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<Integer> grupos;
	
	private Usuario usuario;
	
	public CadastroUsuarioBean() {
		usuario = new Usuario();
		grupos = new ArrayList<>();
		
		for (int i = 0; i < 2; i++) {
			grupos.add(i);
		}
	}
	
	public void salvar() {
		System.out.println("Salvando...");
	}

	public List<Integer> getGrupos() {
		return grupos;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
