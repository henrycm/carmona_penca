<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Menu Distribuidor</title>

	<link rel="stylesheet" href="css/jquery/jquery.ui.all.css">
	<script src="js/jquery/jquery-1.5.1.js"></script>
	<script src="js/jquery/jquery.ui.core.js"></script>
	<script src="js/jquery/jquery.ui.widget.js"></script>
	<script src="js/jquery/jquery.ui.accordion.js"></script>
	<link rel="stylesheet" href="css/jquery/demos.css">
	<script>
	$(function() {
		$( "#accordion" ).accordion({
			autoHeight: false,
			navigation: true
		});
	});
	</script>
</head>
 
<body class="fondo">
<div class="demo">

<div id="accordion">
	<h3><a href="#section1">Administración</a></h3>
	<div><a href="administracion/IndexAfiliado?accion=I" target="mainFrame">Ingresar Afiliado</a>
	<br/>
	<a href="administracion/IndexAfiliado?accion=C" target="mainFrame">Actualizar Afiliado</a>
	<br/>
	<a href="administracion/IndexAfiliado?accion=X" target="mainFrame">Eliminar Afiliado</a>
	
	<br/>
	<a href="administracion/IndexAfiliado?accion=N" target="mainFrame">Listar Afiliados por distribuidor</a>
	<br/>
	
	
	<a href="administracion/IndexAfiliado?accion=E" target="mainFrame">Cons. Afiliado por Codigo</a>
	
	<br/>
	
	<a href="administracion/IndexAfiliado?accion=D" target="mainFrame">Actualizar Afiliado a Distribuidor</a>
	
	<br/>
	
	<a href="administracion/IndexAfiliado?accion=F" target="mainFrame">Listar Distribuidores</a>
<br/>
	
	<a href="administracion/IndexAfiliado?accion=W" target="mainFrame">Activar Afiliac. por Distribuidor</a>
	<br/>
	
	<a href="administracion/IndexAfiliado?accion=U" target="mainFrame">Cambiar Documento Afiliado</a>
	
	<br/>

	<a href="administracion/IndexAfiliado?accion=U" target="mainFrame">Cambiar Cantidad Afiliaciones Distribuidor</a>
	
		<br/>
	
	<a href="administracion/IndexAfiliado?accion=Z" target="mainFrame"> Afiliaciones por distribuidor Periodo</a>
	
	</div>
	
	
	<h3><a href="#section2">Productos</a></h3>
	<div><a href="administracion/producto/IndexProducto?accion=I" target="mainFrame">Administrar Productos</a>
	<br />
	<a href="administracion/producto/IndexProducto?accion=P" target="mainFrame">Productos Consumidos Periodo</a>

	</div>

	
	<h3><a href="#section2">Usuario</a></h3>
	<div><a href="administracion/UsuarioFrontController?accion=C" target="mainFrame">Administrar de Usuarios</a>
	<br />
	</div>
	
<h3><a href="#section2">Pedidos y Consumos</a></h3>
	<div>
	<a href="pedido/IndexPedido?accion=C" target="mainFrame">Realizar Pedido</a>
	
	<br/>
	
		<a href="pedido/IndexPedido?accion=S" target="mainFrame">OPCION NUEVA PARA Realizar Pedido</a>
	
	<br/>
	<a href="pedido/ListaPedidoController?accion=L" target="mainFrame">Actualizar Transporte Pedido</a>
   <br />
   <a href="consumo/IndexConsumo?accion=C" target="mainFrame">Consumos Totales por Distribuidor</a>
	<br>
	<a href="consumo/IndexConsumo?accion=E" target="mainFrame">Eliminar Consumo Afiliado</a>
	<br/>
	<a href="consumo/IndexConsumo?accion=Y" target="mainFrame">Registrar Consumo Afiliado</a>
	<br/>
	<a href="consumo/IndexConsumo?accion=X" target="mainFrame">Eliminar Consumo Afiliado Por Distribuidor</a>
	<br/>
	<a href="consumo/IndexConsumo?accion=Z" target="mainFrame">Reporte de totales por producto por periodo</a>
	<br/>
	</div>
	
	<h3><a href="#section2">Compensación</a></h3>
	<div>
		<a href="reporte/IndexReporteCompensacion?accion=C" target="mainFrame">Compensación afiliados Por Red</a>
	  <br />
	  	<a href="compensacion/ValidarCompensacionDistribuidor?accion=*" target="mainFrame">Verificar Compensacion Distribuidor</a>
	<br/>
	  <div>
		<a href="reporte/IndexReporteCompensacion?accion=F" target="mainFrame">Compensación total Por Distribuidor</a>
	  <br />
	</div>

	<h3><a href="#section2">Liquidación</a></h3>
	<div>
	<a href="liquidacion/IndexLiquidacion" target="mainFrame">Liquidacion Por Red</a>
	<br/>
	<a href="liquidacion/IndexLiquidacion?accion=M" target="mainFrame">Liquidacion Masiva</a>
	<br/>
	</div>
	
	<h3><a href="#section2">Arbol</a></h3>
	<div>
	<a href="jerarquia/IndexArbolController" target="mainFrame">Consultar Jerarquia</a>
	</div>	
	
	</div>

	
	
</div>

</div>



<div class="demo-description">

</div><!-- End demo-description -->

</body>
</html>
