<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
	
 	<script src="../js/liquidacion/premiacion.js"></script>
 	<script src="../js/generico.js"></script>
 
	<link rel="stylesheet" href="../css/jquery/demos.css">

	<link rel="stylesheet" href="../css/multinivel.css">
	


</head>

<body class="fondo" onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
 <form name="liquidacion" method="post" action="ControlLiquidacion">
 <div align="right" id="opcion">
		<a href="javascript:generarReportePeriodoAfiliado();">Generar Reporte</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
 <input name="accion" type="hidden" value="<c:out value="${accion}"/>">
  <input name="periodo" type="hidden">
 
 <div class="demo" align="center">
    <div class="titulo">Consultar Premiacion del afiliado por periodo</div>
     <table class="tabla" width="90%">
     <tr>
     
    
     </tr>
     <tr>
     
     <td>
       
     
         Periodo:
         <select name="mes">
         
         </select>
         <select name="ano">
         </select>  
  
     
     </td>
     </tr>  
     
          <tr>
     
     <td>
       
     
         Tipo Reporte:
         <input type="radio" name="tipoReporte" value="PDF" checked="checked"> PDF
   		<input type="radio" name="tipoReporte" value="Excel"> Excel
     
     </td>
     </tr> 
     
   </table>  
       
 </form>
</body>
</html>