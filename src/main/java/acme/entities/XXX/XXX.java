package acme.entities.XXX;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class XXX extends DomainEntity{

	protected static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date date;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date moment;
	
	@NotNull
	@Valid
	protected Money money;
	
	@NotNull
	protected Boolean flag;
}
