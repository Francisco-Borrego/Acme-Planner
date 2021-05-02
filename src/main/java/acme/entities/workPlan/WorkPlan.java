package acme.entities.workPlan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.Manager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class WorkPlan extends DomainEntity  {
	
	//Serialisation identifier
	
	protected static final long serialVersionUID = 1L;
	
	//Attributes
	
	@ManyToOne
	private Manager manager;
	
	@ManyToMany
	private Set<Task> tasks;
	
	@NotNull
	private Boolean isPublic;
	
	@NotNull
	private Boolean isPublished;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date startExecutionPeriod;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date endExecutionPeriod;
	
	@Transient
	public Double getWorkload() {
		Double res = 0.;
        Integer parteEntera;
        Integer parteFraccional;

        for(final Task task: this.tasks) {
            final Double workload = task.getWorkload();
            parteEntera = workload.intValue(); 
            parteFraccional = (int) ((workload - parteEntera)*100);
            res += (parteEntera*60 + parteFraccional);
        }

        Integer horas=0;
        Double minutos;
        while(res>60) {
            res-=60;
            horas+=1;
        }
        minutos = res/100;
        res=horas+minutos;
        return res;
	}
	
	public Boolean isValid() {
		Boolean res = true;
		
		// Restriccion 1, si es privado no puede contener tasks publicas
		if(!this.isPublic) {
			for(final Task task: this.tasks) {
				res = res && !task.getIsPublic();
				if(!res) {
					return res;
				}
			}
		}
		
		// Restriccion 2, el tiempo de ejecución tiene que contener los tiempos de ejecucion de cada tarea
		for(final Task task: this.tasks) {
			res = res && this.startExecutionPeriod.before(task.getStartExecutionPeriod())
				&& this.endExecutionPeriod.after(task.getEndExecutionPeriod());
			if(!res) {
				return res;
			}
		}
		
		return res;
	}
	
	public void setStartExecutionPeriodAndEndExecutionPeriodRand() {
		List<Task> tasks1 = this.tasks.stream().collect(Collectors.toList());
		Date start1 = tasks1.get(0).getStartExecutionPeriod();
		Date finish1 = tasks1.get(0).getEndExecutionPeriod();
		for(Task task:tasks1) {
			if(task.getStartExecutionPeriod().before(start1)) {
				start1=task.getStartExecutionPeriod();
			}
			else if(task.getEndExecutionPeriod().after(finish1)) {
				finish1 = task.getEndExecutionPeriod();
			}
		}
		Calendar c = Calendar.getInstance();
		c.setTime(start1);
		c.set(Calendar.HOUR_OF_DAY, 8);
		c.set(Calendar.MINUTE, 00);
		c.add(Calendar.DATE, -1);
		start1 = c.getTime();
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(finish1);
		c2.set(Calendar.HOUR_OF_DAY, 17);
		c2.set(Calendar.MINUTE, 00);
		c2.add(Calendar.DATE, 1);
		finish1 = c2.getTime();

		this.startExecutionPeriod = start1;
		this.endExecutionPeriod = finish1;
	}
}
