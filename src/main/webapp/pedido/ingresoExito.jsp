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
	<link rel="stylesheet" href="../css/jquery/demos.css">


</head>
<body class="fondo">
 <div class="demo" align="center">
   <table class="tabla">
     <tr>
     
     <td>
     El pedido a la empresa MULTIALOE fue exitoso.
     Desea Imprimir la constancia.   <a href="javascript:imprimirConstanciaPedido()" target="mainFrame">IMPRIMIR CONSTANCIA PEDIDO</a>
     <form name="imprimirPedido" method="post">
     <input type="hidden" name="codigoPedido" value='<c:out value="${codigoPedido}"></c:out>'>
     </form>
     </td>
     </tr>
    
   </table>    
   </div>
</body>
</html>