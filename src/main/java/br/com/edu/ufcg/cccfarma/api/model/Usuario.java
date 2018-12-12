package br.com.edu.ufcg.cccfarma.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = -2327919147183119418L;

	@NotEmpty
	private String nome;
	
	@Id
    @NotEmpty
	private String cpf;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
    @JsonIgnore
	private String senha;
	
	@NotEmpty
	private String endereco;
	
	@NotNull
	private boolean admin;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date cadastro;

}
