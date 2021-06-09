package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.markus.Marku;
import acme.entities.shouts.Shout;
import acme.features.configuration.ConfigurationService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected AnonymousShoutRepository shoutRepository;

	@Autowired
	protected ConfigurationService confService;
		
	// AbstractCreateService<Administrator, Shout> interface ------------------------
		
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;
			
		return true;
	}
		
	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
			
		request.bind(entity, errors);
	}
		
	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
			
		request.unbind(entity, model, "author", "text", "info", "marku");
	}
		
	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;
		
		Shout result;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Shout();
		result.setMoment(moment);

		return result;
	}
	
	@Override 
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("text")) {
			final boolean umbralSuperado = this.confService.spamFilter(entity.getText());
			errors.state(request, !umbralSuperado,"text", "manager.task.error.umbral-superado");
		}
		
		if(!errors.hasErrors("marku.name")) {
			final Optional<Marku> opt = this.shoutRepository.findShoutByMarkuName(entity.getMarku().getName());
			
			final String pattern = entity.getMarku().getName();
			final Integer year = Integer.valueOf("20" + pattern.substring(2, 4));
			final Integer month = Integer.valueOf(pattern.substring(4, 6));
			final Integer day = Integer.valueOf(pattern.substring(6, 8));
			final Boolean comp = LocalDate.now().getYear()==year && LocalDate.now().getMonthValue()==month && 
				LocalDate.now().getDayOfMonth()==day;
			
			errors.state(request, comp, "marku.name", "anonymous.shout.error.incorrect-pattern");
			errors.state(request, !opt.isPresent(), "marku.name", "anonymous.shout.error.repeated-marku");
		}
		
		if(!errors.hasErrors("marku.deadline")) {
			final Date deadline = entity.getMarku().getDeadline();
			final Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.WEEK_OF_MONTH, +1);
			errors.state(request, !deadline.before(calendar.getTime()), "marku.deadline", "anonymous.shout.error.incorrect-deadline");
		}
		
		if(!errors.hasErrors("marku.budget")) {
			final String currency = entity.getMarku().getBudget().getCurrency();
			errors.state(request, currency.equals("EUR") || currency.equals("USD"), "marku.budget", "anonymous.shout.error.incorrect-currency");
		}
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		final Marku m = entity.getMarku();
		this.shoutRepository.save(m);
		this.shoutRepository.save(entity);
	}


}
