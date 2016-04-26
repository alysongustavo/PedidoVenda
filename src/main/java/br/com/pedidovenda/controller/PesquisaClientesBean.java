package br.com.pedidovenda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PesquisaClientesBean {
	
	private List<Integer> clientesFiltrados;
	
	public PesquisaClientesBean() {
		clientesFiltrados = new ArrayList<>();
		
		for (int i = 0; i < 30; i++) {
			clientesFiltrados.add(i);
		}
	}

	public List<Integer> getClientesFiltrados() {
		return clientesFiltrados;
	}
	
}
