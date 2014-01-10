<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
	
 
 	<script src="../js/liquidacion/liquidacion.js"></script>
 	<script src="../js/generico.js"></script>
 
	<link rel="stylesheet" href="../css/jquery/demos.css">

	<link rel="stylesheet" href="../css/multinivel.css">
	
	
 

</head>
<body class="fondo" onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
 <form name="liquidacion" method="post" action="ControlLiquidacion">
 <div align="right" id="opcion">
		<a href="javascript:enviarRed();">Generar Liquidacion</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
 <div class="demo" align="center">
    LIQUIDACION POR RED DE DISTRIBUCION
    
    <c:if test="${liquidacionExitosa==null ||liquidacionExitosa==''}"> 
    
   <table class="tabla" width="90%">
     <tr>
     
    
     </tr>
     <tr>
     
     <td>
       
     Distribuidor a consultar: <select name="red">
     <option value="">Seleccione el distribuidor</option>
    
        <c:forEach var='distribuidor'   items='${listaDistribuidores}'>
     <option value="<c:out value='${distribuidor.cedula}'/>"><c:out value='${distribuidor.nombre}'/>&nbsp;<c:out value='${distribuidor.apellido}'/></option>
        </c:forEach>
      
       </select> 
     
     
         Periodo:
         <select name="mes">
         
         </select>
         <select name="ano">
         </select>  
     </td>
     </tr>  
   </table>  
</c:if>   
   
<c:if test="${liquidacionExitosa!=null}"> 
     
   <table align="center" class="tabla" width="90%" border="1">
     <tr>
     
       <td  width="10%" >
         La liquidacion fue Exitosa.
       </td>
        
     </tr>
  
     
   </table>

</c:if>  
 </form>
</body>
</html>