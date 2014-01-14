<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default" role="navigation">
    <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">      
      <li class="dropdown">
        <a target="mainFrame" href="#" class="dropdown-toggle" data-toggle="dropdown">Administración <b class="caret"></b></a>
        <ul class="dropdown-menu">
	    <li><a target="mainFrame" href="administracion/IndexAfiliado?accion=I" >Ingresar Afiliado</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=C" >Actualizar Afiliado</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=X" >Eliminar Afiliado</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=N" >Listar Afiliados por distribuidor</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=E" >Cons. Afiliado por Codigo</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=D" >Actualizar Afiliado a Distribuidor</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=F" >Listar Distribuidores</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=W" >Activar Afiliac. por Distribuidor</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=U" >Cambiar Documento Afiliado</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=U" >Cambiar Cantidad Afiliaciones Distribuidor</a></li>
		<li><a target="mainFrame" href="administracion/IndexAfiliado?accion=Z" > Afiliaciones por distribuidor Periodo</a></li> 
        </ul>
      </li>
    </ul>

	<ul class="nav navbar-nav">
		<li class="dropdown"><a target="mainFrame" href="#" class="dropdown-toggle"
		data-toggle="dropdown">Productos <b class="caret"></b></a>
			<ul class="dropdown-menu">
 				<li><a target="mainFrame" href="administracion/producto/IndexProducto?accion=I" >Administrar Productos</a></li>	
				<li><a target="mainFrame" href="administracion/producto/IndexProducto?accion=P" >Productos Consumidos Periodo</a></li>
			</ul>
		</li>
	</ul>

	<ul class="nav navbar-nav">
		<li class="dropdown"><a target="mainFrame" href="#" class="dropdown-toggle"
		data-toggle="dropdown">Usuario <b class="caret"></b></a>
			<ul class="dropdown-menu">
 				<li><a target="mainFrame" href="administracion/UsuarioFrontController?accion=C" >Administrar de Usuarios</a></li>
			</ul>
		</li>
	</ul>

	<ul class="nav navbar-nav">
		<li class="dropdown"><a target="mainFrame" href="#" class="dropdown-toggle"
		data-toggle="dropdown">Pedidos y Consumos <b class="caret"></b></a>
			<ul class="dropdown-menu">
 					<li><a target="mainFrame" href="pedido/IndexPedido?accion=C" >Realizar Pedido</a></li>		
					<li><a target="mainFrame" href="pedido/IndexPedido?accion=S" >OPCION NUEVA PARA Realizar Pedido</a></li>
					<li><a target="mainFrame" href="pedido/ListaPedidoController?accion=L" >Actualizar Transporte Pedido</a></li>   
				    <li><a target="mainFrame" href="consumo/IndexConsumo?accion=C" >Consumos Totales por Distribuidor</a></li>	
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=E" >Eliminar Consumo Afiliado</a></li>	
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=Y" >Registrar Consumo Afiliado</a></li>	
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=X" >Eliminar Consumo Afiliado Por Distribuidor</a></li>	
					<li><a target="mainFrame" href="consumo/IndexConsumo?accion=Z" >Reporte de totales por producto por periodo</a></li>
			</ul>
		</li>
	</ul>

	<ul class="nav navbar-nav">
		<li class="dropdown"><a target="mainFrame" href="#" class="dropdown-toggle"
		data-toggle="dropdown">Liquidación <b class="caret"></b></a>
			<ul class="dropdown-menu">
 				<li><a target="mainFrame" href="liquidacion/IndexLiquidacion" >Liquidacion Por Red</a></li>	
				<li><a target="mainFrame" href="liquidacion/IndexLiquidacion?accion=M" >Liquidacion Masiva</a></li>
			</ul>
		</li>
	</ul>

	<ul class="nav navbar-nav">
		<li class="dropdown"><a target="mainFrame" href="#" class="dropdown-toggle"
		data-toggle="dropdown">Arbol <b class="caret"></b></a>
			<ul class="dropdown-menu">
 				<li><a target="mainFrame" href="jerarquia/IndexArbolController" >Consultar Jerarquia</a></li>
			</ul>
		</li>
	</ul>
					
</div>
</nav>
