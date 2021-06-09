<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author" placeholder="John Doe"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text" placeholder="Lorem ipsum"/>
	<acme:form-textbox code="anonymous.shout.form.label.info" path="info" placeholder="http://www.prueba.com"/>
	
	<acme:form-textbox code="anonymous.shout.form.label.name" path="marku.name" placeholder="XXYYMMDDZZ where XX is anything except words, and ZZ is only words"/>
	<acme:form-moment code="anonymous.shout.form.label.deadline" path="marku.deadline" placeholder="YYYY/MM/DD hh:mm"/>
	<acme:form-money code="anonymous.shout.form.label.budget" path="marku.budget" placeholder="100 EUR o 100 USD"/>
	<acme:form-checkbox code="anonymous.shout.form.label.important" path="marku.important"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>