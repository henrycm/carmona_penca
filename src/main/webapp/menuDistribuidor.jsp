<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Afiliación</a>
				<ul class="dropdown-menu">
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
					<li><a href="pedido/IndexPedido?accion=Q" target="mainFrame">OPCIÓN
							NUEVA PARA Realizar Pedido</a></li>
					<li><a href="pedido/IndexPedido?accion=E" target="mainFrame">Eliminar
							Pedido</a></li>
					<li><a target="mainFrame"
						href="${ctx}/spring/inventarioDistribuidor/inicio">Consultar
							Inventario Distribuidor</a></li>
					<li><a href="consumo/IndexConsumo?accion=B" target="mainFrame">Registrar
							Consumo Afiliado</a></li>
					<li><a href="consumo/IndexConsumo?accion=E" target="mainFrame">Eliminar
							Consumo Afiliado</a></li>
					<li><a href="pedido/IndexPedido?accion=L" target="mainFrame">Lista
							de Pedidos por Periodo</a></li>
					<li><a href="consumo/IndexConsumo?accion=L" target="mainFrame">Lista
							de Consumos por Periodo</a></li>
					<li><a href="consumo/IndexConsumo?accion=D" target="mainFrame">Consumos
							Totales por Distribuidor</a></li>
					<li><a href="consumo/IndexConsumo?accion=N" target="mainFrame">Lista
							consumos por Nombre Afiliado</a></li>
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
