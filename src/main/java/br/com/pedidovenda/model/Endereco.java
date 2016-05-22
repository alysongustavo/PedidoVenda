package br.com.pedidovenda.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	private String cep;
	
	private Cliente cliente;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max = 150)
	@Column(nullable = false, length = 150)
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@NotBlank
	@Size(max = 20)
	@Column(nullable = false, length = 20)
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Size(max = 20)
	@Column(length = 150)
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@NotBlank
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotBlank
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	@NotBlank
	@Size(max = 9)
	@Column(nullable = false, length = 9)
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Endereco other = (Endereco) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}
	
}
