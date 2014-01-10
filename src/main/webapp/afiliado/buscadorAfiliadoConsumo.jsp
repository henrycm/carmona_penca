<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/multinivel.css">
	<script src="../js/generico.js"></script>
	<script src="../js/afiliado/afiliado.js"></script>
		<script src="../js/consumo/consumo.js"></script>
		<script src="../js/generico.js"></script>
	
    <link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body
	onload="javascript:cargaPeriodoConsumo('<c:out value="${periodo}"/>');">
<div class="demo">

<form action="../administracion/BuscadorAfiliado" name="buscarAfiliado"
	method="get"><input type="hidden" name="accion" value="A" /> 
  <input name="distribuidor" type="hidden" value="<c:out value="${distribuidor}"/>">
	<input 	type="hidden" name="actualizar" /> 
	<input type="hidden" name="documento" />

<table width="75%" class="tabla">
	<tr>
		<td width="50%">Buscar Afliado</td>
		<td width="50%">
		<table width="200" align="right">
			<tr>
				<td><a href="javascript:buscarAfiliadoConsumo()"> Consultar
				</a></td>
			</tr>
		</table>
		&nbsp;</td>
	</tr>
	<tr>
		<td>C&oacute;digo Empresario:</td>

		<td><input name="codigoEmpresario" type="text" id="codigo"
			value="<c:out value="${documento}"/>" maxlength="11" /> <select
			name="letra">
			<option value="A">A</option>
       <option value="B" >B</option>
           <option value="C" >C</option>
           <option value="D" >D</option>
           <option value="E" >E</option>
           <option value="F" >F</option>
           <option value="G" >G</option>
           <option value="H" >H</option>
           <option value="I" >I</option>
           <option value="J" >J</option>
           <option value="K" >K</option>
           <option value="L" >L</option>
           <option value="M" >M</option>
           <option value="N" >N</option>
           <option value="O" >O</option>


		</select></td>
	</tr>
	<tr>
		<td>Nombre del empresario:</td>

		<td><input name="nombre" type="text" id="nombre"
			value="<c:out value="${nombre}"/>" maxlength="30" /></td>
	</tr>
	<tr>
		<td>Periodo Consumo:</td>
		<td><select name="mes">

		</select> <select name="ano">
		</select> <input name="cedula" type="hidden" id="cedula" /> <input
			name="fechaConsumo" type="hidden" id="fechaConsumo" /></td>
	</tr>

</table>
<br />
<br />
<div class="tituloLista">LISTA AFILIADOS</div>
<c:if test="${listaAfiliados!=null}">

	<table width="100%" border="1" class="tabla">
		<tr>
			<td width="15%">Documento Empresario</td>
			<td width="60%">Nombre Empresario</td>
			<td width="25%">Seleccionar</td>
		</tr>
		<c:forEach var='Afiliado' items='${listaAfiliados}'>
			<tr>
				<td><c:out value='${Afiliado.cedula}' /></td>
				<td><c:out value='${Afiliado.nombre}' />&nbsp;<c:out
					value='${Afiliado.apellido}' /></td>
				<td><a
					href="javascript:seleccionarAfiliado('<c:out value='${Afiliado.cedula}'/>')"
					target="mainFrame">Seleccionar</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if></form>
</div>
</body>
</html>
