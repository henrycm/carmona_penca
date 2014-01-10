<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>MENU AFILIADO - MULTIALOE</title>

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

<h3><a href="#section2"> Consumos</a></h3>

	<div>
	<a href="consumo/IndexConsumo?accion=L" target="mainFrame">Lista de Consumos por Periodo</a>
	
	</div>	
	<h3><a href="#section2">Compensacion</a></h3>
	<div>
	<a href="reporte/IndexReporteCompensacion?accion=B" target="mainFrame">Consultar Compensacion Usuario Periodo</a>
	<br/>
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

</div><!-- End demo -->



<div class="demo-description">

</div><!-- End demo-description -->

</body>
</html>
