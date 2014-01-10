<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/multinivel.css">
 <script src="../js/pedido/pedido.js"></script>
  <script src="../js/generico.js"></script>
 
	<link rel="stylesheet" href="../css/jquery/demos.css">


</head>

  


<!--body class="fondo" onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')"-->
<body class="fondo">
 <form name="liquidacion" method="post" action="ControlLiquidacion">
 <div align="right" id="opcion">
		<a href="javascript:realizarPedidoDistribuidor();">Realizar Pedido</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
 <input name="accion" type="hidden" value="Q">
  <!--input name="periodo" type="hidden"-->
 
 <div class="demo" align="center">
Seleccione el distribuidor para el pedido     <table class="tabla" width="90%">
   
     <!--tr>
        <td>
       
     
         Periodo:
         <select name="mes">
         
         </select>
         <select name="ano">
         </select>  
  
     
     </td>
    
      
          </tr-->
          <tr>
     
     <td>
       
      Distribuidor: <select name="distribuidor">
     <option value="">Seleccione el distribuidor</option>
    
        <c:forEach var='distribuidor'   items='${listaDistribuidores}'>
     			<option value="<c:out value='${distribuidor.cedula}'/>">
     			   <c:out value='${distribuidor.nombre}'/>&nbsp;<c:out value='${distribuidor.apellido}'/>
     			</option>
        </c:forEach>
      
       </select> 
        
     
     </td>
     </tr> 
     
   </table>  
       
 </form>
</body>


</html>