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
	<h3><a href="#section1">Afiliación</a></h3>
	<div>
    <!--Suspende Opcion de Afiliar al Perfil Distribuidor; Solo Puede Afiliar el Administrador.-->
    <!--a href="administracion/IndexAfiliado?accion=I" target="mainFrame">Ingresar Afiliado</a>
	<br/-->
    
	<a href="administracion/IndexAfiliado?accion=C" target="mainFrame">Actualizar Afiliado</a>
	<br/>
	
	<a href="administracion/IndexAfiliado?accion=E" target="mainFrame">Cons. Afiliado por Codigo</a>
	
	</div>
	
    
<h3><a href="#section2">Pedidos y Consumos</a></h3>
	<div>
	<!--a href="pedido/IndexPedido?accion=I" target="mainFrame">Realizar Pedido</a>
	
	<br/>
	
		<a href="pedido/IndexPedido?accion=Q" target="mainFrame">OPCION NUEVA PARA Realizar Pedido</a>
	
	<br/>
	<a href="pedido/IndexPedido?accion=E" target="mainFrame">Eliminar Pedido</a>
	
	<br/-->
	<a href="consumo/IndexConsumo?accion=B" target="mainFrame">Registrar Consumo Afiliado</a>
	<br/>

	<a href="consumo/IndexConsumo?accion=E" target="mainFrame">Eliminar Consumo Afiliado</a>
	<br/>
	<a href="pedido/IndexPedido?accion=L" target="mainFrame">Lista de Pedidos por Periodo</a>
	
	<br/>
	<a href="consumo/IndexConsumo?accion=L" target="mainFrame">Lista de Consumos por Periodo</a>
	 <br />
   <a href="consumo/IndexConsumo?accion=D" target="mainFrame">Consumos Totales por Distribuidor</a>
   
	 <br />
   <a href="consumo/IndexConsumo?accion=N" target="mainFrame">Lista consumos por Nombre Afiliado</a>
	</div>
	
	<h3><a href="#section2">Compensacion</a></h3>
	<div>
	<a href="reporte/IndexReporteCompensacion?accion=B" target="mainFrame">Consultar Compensación Usuario Periodo</a>
	<br/>
	<a href="reporte/IndexReporteCompensacion?accion=D" target="mainFrame">Pago de Compensación afiliados Periodo</a>

	</div>
	
	<h3><a href="#section2">Premiacion</a></h3>
	<div>
	<a href="reporte/IndexPremiacion?accion=A" target="mainFrame">Consultar Premiacion Usuario</a>
	<br/>
	</div>
	
	<h3><a href="#section2">Arbol</a></h3>
	<div>
	<a href="jerarquia/IndexArbolController?accion=A" target="mainFrame">Consultar Jerarquia</a>
	</div>		
	
</div>

</div>



<div class="demo-description">

</div><!-- End demo-description -->

</body>
</html>
