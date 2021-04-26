package acme.entities.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends DomainEntity {
	
	//Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	// Atributos
	
	@NotNull
	private String spamWords;
	
//	@NotNull
//	@Range(min=0, max=100)
//	private Double umbral;
}
