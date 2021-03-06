<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default">
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Administración</a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Usuarios</li>
					<li><a target="mainFrame"
						href="administracion/UsuarioFrontController?accion=C">Administrar
							Usuarios</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Afiliados</li>
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=I">Ingresar Afiliado</a></li>
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=C">Actualizar
							Afiliado</a></li>
					<!-- <li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=X">Eliminar Afiliado</a></li> -->
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=E">Consultar
							Afiliado</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Distribuidores</li>
					<!-- <li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=D">Actualizar
							Afiliado a Distribuidor</a></li> -->
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=W">Activar
							Afiliaciones Por Distribuidor</a></li>
					<!--<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=U">Cambiar Documento
							Afiliado</a></li>-->
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=F">Listar
							Distribuidores</a></li>
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=N">Listar Afiliados
							Por Distribuidor</a></li>
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=Z"> Listar
							Afiliaciones Por Distribuidor Periodo</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Productos</li>
					<li><a target="mainFrame"
						href="administracion/producto/IndexProducto?accion=I">Administrar
							Productos</a></li>
					<li><a target="mainFrame"
						href="administracion/producto/IndexProducto?accion=P">Productos
							Consumidos Periodo</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Pedidos y
					Consumos</a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Pedidos</li>
					<li><a target="mainFrame" href="pedido/IndexPedido?accion=C">Realizar
							Pedido</a></li>
					<li><a target="mainFrame" href="pedido/IndexPedido?accion=S">Realizar
							Pedido Opción Nueva</a></li>
					<li><a target="mainFrame"
						href="pedido/ListaPedidoController?accion=L">Actualizar
							Transporte Pedido</a></li>
					<li><a href="pedido/IndexPedido?accion=E" target="mainFrame">Eliminar
							Pedido</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Consumos</li>
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=Y">Registrar
							Consumo Afiliado</a></li>
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=E">Eliminar
							Consumo Afiliado</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Reportes</li>
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=C">Reporte
							Consumos Totales por Distribuidor</a></li>
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=Z">Reporte
							de Totales por Producto por Periodo</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Abonos \
					Inventarios</a>
				<ul class="dropdown-menu">
					<li><a target="mainFrame"
						href="${ctx}/spring/movimiento/inicio">Realizar Abono</a></li>
					<li class="divider"></li>
					<li><a target="mainFrame"
						href="${ctx}/spring/inventarioDistribuidor/inicio">Consultar
							Inventario Distribuidor</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Compensación</a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Liquidación</li>
					<li><a target="mainFrame"
						href="liquidacion/ControlLiquidacion?accion=I">Calcular Árbol</a></li>
					<li><a target="mainFrame" href="liquidacion/IndexLiquidacion">Liquidación
							Por Red</a></li>
					<li><a target="mainFrame"
						href="liquidacion/IndexLiquidacion?accion=M">Liquidación
							Masiva</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Compensación</li>
					<li><a
						href="compensacion/ValidarCompensacionDistribuidor?accion=*"
						target="mainFrame">Verificar Compensación Distribuidor</a></li>
					<li><a href="reporte/IndexReporteCompensacion?accion=C"
						target="mainFrame">Compensación Afiliados Por Red</a></li>
					<li><a href="reporte/IndexReporteCompensacion?accion=F"
						target="mainFrame">Compensación Total Por Distribuidor</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Árbol</li>
					<li><a target="mainFrame"
						href="jerarquia/IndexArbolController">Consultar Jerarquía</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Parametrización</a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Parámetros</li>
					<li><a target="mainFrame"
						href="administracion/parametro/IndexFrontController">Consulta
							y Modificación de Parámetros</a></li>
				</ul></li>
		</ul>
	</div>
</nav>
