<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="co.com.multinivel.helper.UsuarioHelper"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Documento sin t&iacute;tulo</title>
<link rel="stylesheet" href="css/multinivel.css">
</head>

<body>
<div id="div_encabezado" align="left">
<table width="100%">
  
  <tr>
    <td width="35%"><img src="images/penca.png" width="80" height="80" /><img src="images/Multi-Aloe.png" width="220" height="48"  /></td>
    <td width="50%">
    <span class="tituloPequeno">Red Fenix Multi  - ALOE 
NIT:900 542 097-4,  Autopista Medellin Bogota km. 29 <br> Telefax:5628486 Tel:5631489, cel: 311 376 44 20 </br> e-mail:pedidos@redfenix.com.co
 <br/><span>Usuario  :<%=UsuarioHelper.getUsuario()%></span>

</span>
</td>
<td width="15%">
<a href="<c:url value="j_spring_security_logout"/>" target="_parent">Salida Segura</a> 


</td>

  </tr>
</table>




</div>


</body>

</html>
