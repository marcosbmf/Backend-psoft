package CCCFarma.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	@Id
	private String cpf;
	
	private String email;
	
	private String senha;
	
	private String endereco;
	
	private boolean admin;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date cadastro;

}
