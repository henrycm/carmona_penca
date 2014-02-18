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
<c:if test="${liquidacionExitosa==null || liquidacionExitosa==''}"> 

 <form name="liquidacion" method="post" action="ControlLiquidacion">
 <c:if test="${empty mensaje}">
	 <div align="right" id="opcion">
			<a href="javascript:enviarMasivo();">Generar Liquidacion</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
</c:if>
 <input name="accion" type="hidden" value="M">
 <div class="demo" align="center">
    LIQUIDACION MASIVA
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
   </table>  
  </form>
</c:if>   
  
   
<c:if test="${liquidacionExitosa!=null && liquidacionExitosa=='1'}"> 
     
   <table align="center" class="tabla" width="90%" border="1">
     <tr>
     
       <td  width="10%" >
         La liquidacion fue Exitosa.
       </td>
        
     </tr>
  
     
   </table>

</c:if>  
<c:if test="${liquidacionExitosa!=null && liquidacionExitosa=='2'}"> 
     
   <table align="center" class="tabla" width="90%" border="1">
     <tr>
     
       <td  width="10%" >
         La liquidacion ya habia sido realizada.
       </td>
        
     </tr>
  
     
   </table>

</c:if>  

<c:if test="${liquidacionExitosa!=null && liquidacionExitosa=='0'}"> 
     
   <table align="center" class="tabla" width="90%" border="1">
     <tr>
     
       <td  width="10%" >
         La liquidacion presento errores por favor comuniquese con el administrador.
       </td>
        
     </tr>
  
     
   </table>

</c:if>  

<c:if test="${not empty mensaje}">
	   <table align="center" class="tabla" width="90%" border="1">
     <tr>     
       <td  width="10%" >
         <c:out value="${mensaje}"></c:out>
       </td>        
     </tr>     
   </table>
</c:if>

 
</body>
</html>