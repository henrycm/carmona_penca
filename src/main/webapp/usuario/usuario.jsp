<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
    
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" type="text/css" href="../css/multinivel.css" />
<title>Administración de usuarios</title>
<link rel="stylesheet" href="../css/multinivel.css">
<link rel="StyleSheet" type="text/css" href="${ctx}/bootstrap/css/bootstrap.css"></link>
<script src="../js/generico.js"></script>
<script src="../js/usuario/usuario.js"></script>
</head>

<body class="fondo">
<div class="demo">
<form id="form1" name="form1" method="post" action="UsuarioFrontController">
    <input name="accion" type="hidden" id="accion">

<c:if test='${listaUsuarios!=null}'> 
	<div align="right" id="opcion">
			<!--a href="javascript:enviarUsuaro('I');">Ingresar</a-->
			<a href="javascript:enviarUsuaro('A');">Actualizar</a>
			<a href="javascript:enviarUsuaro('B');">Borrar</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	
	<table align="center" class="tabla" width="81%" border="1">
	  <tr>
	    <th colspan="15" >ADMINISTRACION DE USUARIOS</th>
	  </tr>
	  <tr>
	    <th class="encabezadoiz" width="17%">&nbsp;Login:</th>
	    <td colspan="5"><input type="text" name="usuario" id="usuario"/></td>
	  </tr>
	  <tr>
	    <th class="encabezadoiz">&nbsp;Clave:</th>
	    <td colspan="2">
	    	<input type="text" class="inputtext" name="password" id= "password"/>
	    </td>
	  </tr>
	<tr>
	<td align="center" colspan="2">
		
	</tr>
	</table>
</c:if>
</form>

<form class="form-inline" role="form" action="UsuarioFrontController" method="POST">
  <input name="accion" type="hidden" value="C">
  <fieldset>  
  <div class="form-group">    
    <select name="nomFiltro" class="form-control input-sm">
    	<option value="Nombre">Nombre</option>
    	<option value="Apellido">Apellido</option>
    	<option value="Cedula">Cedula</option>
    </select>    
  </div>
  <div class="form-group">
  	<input type="text" class="form-control input-sm" name="filtro"  required="required" pattern=".{3,}" title="Minimo 3 caracteres" maxlength="50">
  </div>
  <div class="form-group">
  	<button type="submit" class="btn btn-primary btn-sm" value="Check">Buscar</button>
  </div>
  </fieldset>
</form>

<c:if test='${listaUsuarios!=null}'> 
   <table align="center" class="table table-striped" width="81%" border="1">
     <tr>
       <th class="encabezadoiz">
        Login
       </th>
       <th class="encabezadoiz">
          Clave
       </th>
        <th class="encabezadoiz">
          Distribuidor
       </th>      
        <th class="encabezadoiz">
          Clave Distribuidor
       </th> 

       <th class="encabezadoiz" colspan=2>
		Activado
       </th>
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
        <td>
		<a href="javascript:cargarUsuario('<c:out value='${usuario.username}'/>','<c:out value='${usuario.password}'/>','<c:out value='${usuario.enabled}'/>')">Seleccionar</a>
       </td>
     </tr>
     </c:forEach>
     
   </table>   
		
	</c:if> 
</div>
</body>
</html>	