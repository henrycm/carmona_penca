<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Afiliados</a>
				<ul class="dropdown-menu">
					<li><a href="administracion/IndexAfiliado?accion=I"
						target="mainFrame">Ingresar Afiliado</a></li>
					<li><a href="administracion/IndexAfiliado?accion=C"
						target="mainFrame">Actualizar Afiliado</a></li>
					<li><a href="administracion/IndexAfiliado?accion=E"
						target="mainFrame">Consultar Afiliado</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Pedidos y
					Consumos</a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Consumos</li>
					<li><a href="consumo/IndexConsumo?accion=B" target="mainFrame">Registrar
							Consumo Afiliado</a></li>
					<li><a href="consumo/IndexConsumo?accion=E" target="mainFrame">Eliminar
							Consumo Afiliado</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Reportes</li>
					<li><a href="pedido/IndexPedido?accion=L" target="mainFrame">Reporte
							de Pedidos por Periodo</a></li>
					<li><a href="consumo/IndexConsumo?accion=L" target="mainFrame">Reporte
							de Consumos por Periodo</a></li>
					<li><a href="consumo/IndexConsumo?accion=D" target="mainFrame">Reporte
							Consumos Totales por Distribuidor</a></li>
					<li><a href="consumo/IndexConsumo?accion=N" target="mainFrame">Reporte
							de Consumos por Nombre Afiliado</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Inventarios</a>
				<ul class="dropdown-menu">
					<li><a target="mainFrame"
						href="${ctx}/spring/inventarioDistribuidor/inicio">Consultar
							Inventario Distribuidor</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Compensación</a>
				<ul class="dropdown-menu">
					<li><a href="reporte/IndexReporteCompensacion?accion=B"
						target="mainFrame">Consultar Compensación Usuario Periodo</a></li>
					<li><a href="reporte/IndexReporteCompensacion?accion=D"
						target="mainFrame">Pago de Compensación afiliados Periodo</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Premiación</a>
				<ul class="dropdown-menu">
					<li><a href="reporte/IndexPremiacion?accion=A"
						target="mainFrame">Consultar Premiacion Usuario</a></li>
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
