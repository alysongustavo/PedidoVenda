package br.com.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.pedidovenda.model.Cliente;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	// private List<Endereco> enderecos;
	
	public CadastroClienteBean() {
		cliente = new Cliente();
		// enderecos = new ArrayList<>();
		
		/* for (Endereco endereco : enderecos) {
			cliente.getEnderecos().add(endereco);
		} */
		
	}
	
	public void salvar() {
		System.out.println("Salvando...");
	}

	public Cliente getCliente() {
		return cliente;
	}

	/* public List<Endereco> getEnderecos() {
		return enderecos;
	} */
	
}
