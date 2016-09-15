package br.com.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.service.CadastroUsuarioService;
import br.com.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroUsuarioService usuarioService;

//	private List<Grupo> grupos;
	
	private Usuario usuario;
	
	public CadastroUsuarioBean() {
//		usuario = new Usuario();
//		grupos = new ArrayList<>();
		
		limpar();
	}
	
	public void salvar() {
		usuario = usuarioService.salvar(usuario);
		limpar();
		FacesUtil.addInfoMessage("Usuario salvo com sucesso!");
	}
	
	private void limpar() {
		usuario = new Usuario();
	}

//	public List<Grupo> getGrupos() {
//		return grupos;
//	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
