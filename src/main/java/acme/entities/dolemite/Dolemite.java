package acme.entities.dolemite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dolemite extends DomainEntity{

	protected static final long serialVersionUID = 1L;
	
	@Column(unique=true)
	@Pattern(regexp = "^\\w{6}:(0[1-9]|1[12])\\d{2}:([012]\\d|3[01])$") 
	protected String keylem;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Future
	protected Date deadline;
	
	@NotNull
	@Valid
	protected Money budget;
	
	@NotNull
	protected Boolean important;
}
