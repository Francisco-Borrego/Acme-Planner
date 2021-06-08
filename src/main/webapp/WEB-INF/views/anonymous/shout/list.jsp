<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="10%" />
	<acme:list-column code="anonymous.shout.list.label.text" path="text"  width="50%"/>
	<acme:list-column code="anonymous.shout.list.label.info" path="info" width="30%" />
	<acme:list-column code="anonymous.shout.list.label.fecha" path="xxx.date" width="33%"/>
	<acme:list-column code="anonymous.shout.list.label.moment" path="xxx.moment" width="33%"/>
	<acme:list-column code="anonymous.shout.list.label.amount" path="xxx.money" width="33%"/>
	<acme:list-column code="anonymous.shout.list.label.flag" path="xxx.flag" width="33%"/>
</acme:list>