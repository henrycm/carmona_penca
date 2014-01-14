<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Lista de Pedidos Proveedor</title>
	<link rel="stylesheet" href="../css/multinivel.css">
	<script src="../js/generico.js"></script>
	<script src="../js/pedido/pedido.js"></script>
    <link rel="stylesheet" href="../css/jquery/demos.css">

</head>

<body class="fondo">
<div class="demo">
<form action="ListaPedidoController" name="pedido" method="post">
<input type="hidden"  name="accion" value="<c:out value="${accion}"/>"/>
<input type="hidden"  name="codigoPedido" />
<input type="hidden"  name="distribuidor" />

<div align="center">
      <div class="titulo">
      LISTA DE PEDIDOS DISTRIBUIDOR
      </div>
<c:if test="${listaPedido!=null}"> 
   <table align="center" class="tbl-lista" width="90%" border="1">
     <tr>
       <th>
        Codigo
       </th>
       <th  >
          Fecha
       </th>
       <th>
         Valor Pedido 
       </th>
       <th  >
        Transporte
       </th>
         <th>
         Seleccionar 
       </th>
     </tr>
     <c:forEach var='pedido'   items='${listaPedido}'>
     
          <tr>
       <td>
         <c:out value='${pedido.codigoPedido}'/>
       </td>
       <td>
            <c:out value='${pedido.fecha}'/>
       </td>
       <td>
         <c:out value='${pedido.totalPedido}'/>
       </td>
       <td>
         <c:out value='${pedido.transporte}'/>
       </td>
     
  

       <td>
		<a href="javascript:actualizarTransporte('<c:out value='${pedido.codigoPedido}'/>','<c:out value='${pedido.transporte}'/>','<c:out value='${pedido.distribuidor}'/>')">Eliminar Transporte</a>
       </td>
     </tr>
     </c:forEach>
     
   </table>

</c:if>  
</div>
</form>
</div>
</body>
</html>
