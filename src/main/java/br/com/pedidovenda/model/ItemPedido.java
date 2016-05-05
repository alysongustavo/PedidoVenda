package br.com.pedidovenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer quantidade;
	private BigDecimal valorUnitario;
	
	private Produto produto;
	private Pedido pedido;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		ItemPedido other = (ItemPedido) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

}
