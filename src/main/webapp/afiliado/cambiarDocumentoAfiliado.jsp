<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>APLICACION MULTIALOE</title>
	<meta charset="utf-8">
	<title>jQuery UI Accordion - Default functionality</title>
	<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
	<script src="../js/jquery/jquery-1.5.1.js"></script>
	<script src="../js/jquery/jquery.ui.core.js"></script>
	<script src="../js/jquery/jquery.ui.widget.js"></script>
	<script src="../js/jquery/jquery.ui.accordion.js"></script>
	<script src="../js/jquery/jquery.effects.core.js"></script>
    <script src="../js/jquery/jquery.effects.drop.js"></script>
 <script src="../js/jquery/jquery.ui.datepicker.js"></script>
 
 	<script src="../js/afiliado/afiliado.js"></script>
 	<script src="../js/generico.js"></script>
 
	<link rel="stylesheet" href="../css/jquery/demos.css">

	<link rel="stylesheet" href="../css/multinivel.css">
	<script>
	$(function() {
		$( "#accordion" ).accordion();
	});
	</script>
	<script>
 
 $(function() {
		$( "#fecha" ).datepicker();
		$( "#fecha" ).datepicker( "option", "showAnim", "drop" );
		$( "#fecha1" ).datepicker();
		$( "#fecha1" ).datepicker( "option", "showAnim", "drop" );
		$( "#fecha2" ).datepicker();
		$( "#fecha2" ).datepicker( "option", "showAnim", "drop" );
		$( "#fecha3" ).datepicker();
		$( "#fecha3" ).datepicker( "option", "showAnim", "drop" );
	});
	</script>
 <style>
 <style>
#accordion{
   height:100px;
    
}
</style>
</head>
<c:if test='${afiliado!=null}'>
<body onload=javascript:seleccionarListaDesplegable('<c:out value="${afiliado.departamento}"/>',document.forma.departamento);>
</c:if>
<c:if test='${afiliado==null}'>
<body>
</c:if>
<div align="center">

<form name="forma" action="AfiliadoFrontController" method="post">

<div align="center">
	CAMBIAR DOCUMENTO DE UN AFILIADO
</div>
<div align="left">
<input name="accion" type="hidden" value="<c:out value='${accion}'/>" />

<c:if test='${mensaje==null}'>

 Código Actual Empresario: <input name="codigoEmpresario" type="text" size="30" />

 Nuevo Código Empresario: <input name="nuevoCodigoEmpresario" type="text" size="30" />

<a href="javascript:cambiarDocumentoAfiliado();">Cambiar Documento Afiliado</a>
</c:if>

<c:if test='${mensaje!=null}'>

  <c:out value="${mensaje}"/>
</c:if>


</form>
</body>
</html>


