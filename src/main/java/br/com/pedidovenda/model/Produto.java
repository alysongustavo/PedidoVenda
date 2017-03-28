package br.com.pedidovenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotBlank;

import br.com.pedidovenda.service.NegocioException;
import br.com.pedidovenda.validation.SKU;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String sku;
	private BigDecimal valorUnitario;
	private Integer quantidadeEstoque;
	
	private Categoria categoria;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotBlank(message = "Por favor, informe o SKU")
	@SKU(message = "Por favor, informe um SKU no formato XX9999")
	@Column(nullable = false, length = 20, unique = true)
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();
	}

	@NotNull
	@Column(name="valor_unitario", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@NotNull
	@Min(0)
	@Max(value = 9999, message = "tem um valor muito alto")
	@Column(name="quantidade_estoque", nullable = false, length = 5)
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Produto other = (Produto) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}
	
	public void baixarEstoque(Integer quantidade) {
		int novaQuantidada = getQuantidadeEstoque() - quantidade;
		
		if (novaQuantidada < 0) {
			throw new NegocioException("Não há disponibilidade no estoque de "
					+ quantidade + " itens do produto " + getSku() + ".");
		}
		
		setQuantidadeEstoque(novaQuantidada);
	}
	
	public void adicionarEstoque(Integer quantidade) {
		setQuantidadeEstoque(getQuantidadeEstoque() + quantidade);
	}
	
}
