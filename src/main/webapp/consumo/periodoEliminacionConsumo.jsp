<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
	
 	<script src="../js/consumo/consumo.js"></script>
 	<script src="../js/generico.js"></script>
 
	<link rel="stylesheet" href="../css/jquery/demos.css">

	<link rel="stylesheet" href="../css/multinivel.css">
	


</head>

<body class="fondo" onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
 <form name="liquidacion" method="post" action="ControlLiquidacion">
 <div align="right" id="opcion">
		<a href="javascript:consultarListaConsumosEliminar();">Consultar Consumos</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
 <input name="accion" type="hidden" value="<c:out value="${accion}"/>">
  <input name="distribuidor" type="hidden" value="<c:out value="${distribuidor}"/>">

  <input name="periodo" type="hidden">
  <input name="pedido" type="hidden">
 <input name="distribuidor" type="hidden" value="<c:out value="${distribuidor}"/>">
  
  <input name="totalPedido" type="hidden">

 <div class="demo" align="center">
    <div class="titulo">Eliminar Consumo periodo</div>

   <c:if test="${pedidos==null}">
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

   </c:if>
    
   <c:if test="${consumos!=null}">
         <table class="tabla" width="90%">
         
           <td>Codigo</td>
              <td>Fecha</td>
              
              <td>Nombre</td>
              <td>Total Pedido</td>
               
              <td>Eliminar Pedido</td>
          <c:forEach var='consumo'   items='${consumos}'>
            <tr>
              <td><c:out value='${consumo.codigoConsumo}'/></td>
              <td><c:out value='${consumo.cedulaAfiliado}'/>- <c:out value='${consumo.nombreAfiliado}'/></td>

              <td><c:out value='${consumo.fecha}'/></td>
              <td><c:out value='${consumo.totalPedido}'/></td>
               
              <td><A href="javascript:eliminarConsumo('<c:out value='${consumo.codigoConsumo}'/>')">Eliminar Consumo</A></td>
              
            </tr>
          </c:forEach>
    
    </table> 
   </c:if>
     
 </form>
</body>
</html>