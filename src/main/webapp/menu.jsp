<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default">
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Administraci�n</a>
				<ul class="dropdown-menu">
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=I">Ingresar Afiliado</a></li>
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=C">Actualizar
							Afiliado</a></li>
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=X">Eliminar Afiliado</a></li>
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=E">Consultar
							Afiliado</a></li>
					<li><a target="mainFrame"
						href="administracion/IndexAfiliado?accion=D">Actualizar
							Afiliado a Distribuidor</a></li>
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
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Productos</a>
				<ul class="dropdown-menu">
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
				class="dropdown-toggle" data-toggle="dropdown">Usuario</a>
				<ul class="dropdown-menu">
					<li><a target="mainFrame"
						href="administracion/UsuarioFrontController?accion=C">Administrar
							de Usuarios</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Pedidos y
					Consumos</a>
				<ul class="dropdown-menu">
					<li><a target="mainFrame" href="pedido/IndexPedido?accion=C">Realizar
							Pedido</a></li>
					<li><a target="mainFrame"
						href="${ctx}/spring/movimiento/inicio">Realizar Abono</a></li>
					<li><a target="mainFrame" href="pedido/IndexPedido?accion=S">OPCI�N
							NUEVA PARA Realizar Pedido</a></li>
					<li><a target="mainFrame"
						href="pedido/ListaPedidoController?accion=L">Actualizar
							Transporte Pedido</a></li>
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=C">Consumos
							Totales por Distribuidor</a></li>
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=E">Eliminar
							Consumo Afiliado</a></li>
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=Y">Registrar
							Consumo Afiliado</a></li>
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=X">Eliminar
							Consumo Afiliado Por Distribuidor</a></li>
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=Z">Reporte
							de totales por producto por periodo</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Compensaci�n</a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Liquidaci�n</li>
					<li><a target="mainFrame"
						href="liquidacion/ControlLiquidacion?accion=I">Calcular �rbol</a></li>
					<li><a target="mainFrame" href="liquidacion/IndexLiquidacion">Liquidaci�n
							Por Red</a></li>
					<li><a target="mainFrame"
						href="liquidacion/IndexLiquidacion?accion=M">Liquidaci�n
							Masiva</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Compensaci�n</li>
					<li><a
						href="compensacion/ValidarCompensacionDistribuidor?accion=*"
						target="mainFrame">Verificar Compensaci�n Distribuidor</a></li>
					<li><a href="reporte/IndexReporteCompensacion?accion=C"
						target="mainFrame">Compensaci�n Afiliados Por Red</a></li>
					<li><a href="reporte/IndexReporteCompensacion?accion=F"
						target="mainFrame">Compensaci�n Total Por Distribuidor</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">�rbol</li>
					<li><a target="mainFrame"
						href="jerarquia/IndexArbolController">Consultar Jerarqu�a</a></li>
				</ul></li>
		</ul>

		<ul class="nav navbar-nav">
			<li class="dropdown"><a target="mainFrame" href="#"
				class="dropdown-toggle" data-toggle="dropdown">Parametrizaci�n</a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Par�metros</li>
					<li><a target="mainFrame"
						href="administracion/parametro/IndexFrontController">Consulta
							y modificaci�n de par�metros</a></li>
				</ul></li>
		</ul>
	</div>
</nav>
