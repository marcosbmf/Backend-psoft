package CCCFarma.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2327919147183119418L;

	@NotNull
	private String nome;
	
	@Id
	private String cpf;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String endereco;
	
	@NotNull
	private boolean admin;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date cadastro;

}
