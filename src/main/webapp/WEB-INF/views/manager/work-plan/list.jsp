<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="manager.work-plan.list.label.start-execution-period" path="startExecutionPeriod" width="40%" />
	<acme:list-column code="manager.work-plan.list.label.end-execution-period" path="endExecutionPeriod" width="40%" />
	<acme:list-column code="manager.work-plan.list.label.workload" path="workload" width="20%" />
</acme:list>