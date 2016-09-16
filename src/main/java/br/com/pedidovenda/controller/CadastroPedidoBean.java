package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.EnderecoEntrega;
import br.com.pedidovenda.model.FormaPagamento;
import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.repository.Clientes;
import br.com.pedidovenda.repository.Usuarios;
import br.com.pedidovenda.service.CadastroPedidoService;
import br.com.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private CadastroPedidoService pedidoService;
	
	private Pedido pedido;
	private List<Usuario> vendedores;
	
	public CadastroPedidoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			vendedores = usuarios.vendedores();
		}
	}
	
	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void salvar() {
		pedido = pedidoService.salvar(pedido);
		
		FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome) {
		return clientes.porNome(nome);
	}

	public Pedido getPedido() {
		return pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}
	
}
