<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Consumos</a>
				<ul class="dropdown-menu">
				<li class="dropdown-header">Reportes</li>
					<li><a href="consumo/IndexConsumo?accion=L" target="mainFrame">Reporte
							de Consumos por Periodo</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Compensación</a>
				<ul class="dropdown-menu">
					<li><a href="reporte/IndexReporteCompensacion?accion=B"
						target="mainFrame">Consultar Compensación Usuario Periodo</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Premiación</a>
				<ul class="dropdown-menu">
					<li><a href="reporte/IndexPremiacion?accion=A"
						target="mainFrame">Consultar Premiación Usuario</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Árbol</a>
				<ul class="dropdown-menu">
					<li><a href="jerarquia/IndexArbolController?accion=A"
						target="mainFrame">Consultar Jerarquía</a></li>
				</ul></li>
		</ul>
	</div>
</nav>
