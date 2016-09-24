package br.com.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.service.EmissaoPedidoService;
import br.com.pedidovenda.util.jsf.FacesUtil;
import br.com.pedidovenda.validation.PedidoEdicao;

@Named
@ViewScoped
public class EmissaoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmissaoPedidoService emissaoPedidoService;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	@Inject
	private Event<PedidoAlteradoEvent> event;
	
	public void emitirPedido() {
		pedido.removerItemVazio();
		
		try {
			pedido = emissaoPedidoService.emitir(pedido);
			event.fire(new PedidoAlteradoEvent(pedido));
			
			FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
		} finally {
			pedido.adicionarItemVazio();
		}
	}

}
