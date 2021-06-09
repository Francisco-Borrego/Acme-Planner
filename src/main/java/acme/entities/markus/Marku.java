package acme.entities.markus;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Marku extends DomainEntity {
	
	// Serialisation identifier ------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes --------------------------------------
	
	@Column(unique = true)
	@Pattern(regexp = "^\\w{2}\\d{2}(0[1-9]|1[12])([012]\\d|3[01])\\w{2}$")
	@NotNull
	protected String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date deadline;

	@NotNull
	@Valid
	protected Money budget;
	
	@NotNull
	protected Boolean important;
}
