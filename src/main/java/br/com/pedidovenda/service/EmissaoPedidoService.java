package br.com.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.StatusPedido;
import br.com.pedidovenda.repository.Pedidos;

public class EmissaoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private EstoqueService estoqueService; 
	
	@Inject
	private Pedidos pedidos;
	
	public Pedido emitir(Pedido pedido) {
		pedido = cadastroPedidoService.salvar(pedido);
		
		if (pedido.isNaoEmissivel()) {
			throw new NegocioException("Pedido não pode ser emitido com status "
					+ pedido.getStatus() + ".");
		}
		
		estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatus(StatusPedido.EMITIDO);
		
		pedido = pedidos.guardar(pedido);
		
		return pedido;
	}

}
