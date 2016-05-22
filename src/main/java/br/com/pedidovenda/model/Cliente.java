package br.com.pedidovenda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String documentoReceitaFederal;
	
	private TipoPessoa tipo;
	private List<Endereco> enderecos = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Email
	@NotBlank
	@Size(max = 100)
	@Column(nullable = false, length = 100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@CNPJ
	@NotBlank
	@Size(max = 14)
	@Column(name = "doc_receita_federal", nullable = false, length = 14)
	public String getDocumentoReceitaFederal() {
		return documentoReceitaFederal;
	}
	public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
		this.documentoReceitaFederal = documentoReceitaFederal;
	}

	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	public TipoPessoa getTipo() {
		return tipo;
	}
	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	@NotNull
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		Cliente other = (Cliente) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}
	
}
