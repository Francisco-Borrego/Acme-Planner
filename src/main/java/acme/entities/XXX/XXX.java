package acme.entities.XXX;

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
public class XXX extends DomainEntity{

	protected static final long serialVersionUID = 1L;
	
	@Column(unique=true)
	//@Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))") -> "YYYY-MM-DD"
	@Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$") //-> "DD/MM/YYYY"
	protected String date;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Future
	protected Date moment;
	
	@NotNull
	@Valid
	protected Money money;
	
	@NotNull
	protected Boolean flag;
}
