<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" type="text/css" href="../css/multinivel.css" />
<title>Administraci�n de usuarios</title>
<script language="javascript" src="../js/generico.js"></script>
<script language="javascript" src="../js/usuario/usuario.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">

<link rel="stylesheet" href="../css/multinivel.css">
</head>

<body class="fondo">
<div class="demo">
<form id="form1" name="form1" method="post" action="UsuarioFrontController">
    <input name="accion" type="hidden" id="accion">

<!-- Men� en la parte de arriba -->

<!-- Fin Men� en la parte de arriba -->
<div align="right" id="opcion">
		<!--a href="javascript:enviarUsuaro('I');">Ingresar</a-->
		<a href="javascript:enviarUsuaro('A');">Actualizar</a>
		<a href="javascript:enviarUsuaro('B');">Borrar</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<table align="center" class="tabla" width="81%" border="1">
  <tr>
    <td colspan="15" >ADMINISTRACION DE USUARIOS</td>
  </tr>
  <tr>
    <td class="encabezadoiz" width="17%">&nbsp;Login:</td>
    <td colspan="5"><input type="text" name="usuario" id="usuario"/></td>
  </tr>
  <tr>
    <td class="encabezadoiz">&nbsp;Clave:</td>
    <td colspan="2">
    	<input type="text" class="inputtext" name="password" id= "password"/>
    </td>
  </tr>
 
   
   
<tr width="112">
<td align="center"colspan="2">
	


</tr>
</table>

	<c:if test='${listaUsuarios!=null}'> 
   <table align="center" class="table" width="81%" border="1">
     <tr>
       <td class="encabezadoiz">
        Login
       </td>
       <td class="encabezadoiz">
          Clave
       </td>
        <td class="encabezadoiz">
          Distribuidor
       </td>      
        <td class="encabezadoiz">
          Clave Distribuidor
       </td> 

       <td class="encabezadoiz" colspan=2>
		Activado
       </td>
     </tr>
     <c:forEach var='usuario' items='${listaUsuarios}'>
     
          <tr>
       <td>
         <c:out value='${usuario.username}'></c:out>
       </td>
       <td>
            <c:out value='${usuario.password}'></c:out>

       </td>
         <td>
            <c:out value='${usuario.distribuidor}'></c:out>

       </td>
	   
	        <td>
            <c:out value='${usuario.passwordDistribuidor}'></c:out>

       </td>
       <td>
       <c:if test='${usuario.enabled==1}'>
           <c:out value='Activado'/>
       </c:if>
       </td>
       <c:if test='${usuario.enabled==0}'>
           <c:out value='Desactivado'/>
       </c:if>
       </td>
        <td>
		<a href="javascript:cargarUsuario('<c:out value='${usuario.username}'/>','<c:out value='${usuario.password}'/>','<c:out value='${usuario.enabled}'/>')">Seleccionar</a>
       </td>
     </tr>
     </c:forEach>
     
   </table>

	</c:if> 

</form>
</div>
</body>
</html>	