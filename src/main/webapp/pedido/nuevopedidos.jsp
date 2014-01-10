<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Documento sin t&iacute;tulo</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
<link rel="stylesheet" href="../css/multinivel.css">
<link rel="stylesheet" href="../css/jquery/demos.css">

<script src="../js/jquery/jquery-1.5.1.js"></script>
<script src="../js/jquery/jquery.ui.core.js"></script>
<script src="../js/jquery/jquery.ui.widget.js"></script>
<script src="../js/jquery/jquery.effects.core.js"></script>
<script src="../js/jquery/jquery.effects.drop.js"></script>
<script src="../js/jquery/jquery.ui.mouse.js"></script>
<script src="../js/jquery/jquery.ui.draggable.js"></script>
<script src="../js/jquery/jquery.ui.droppable.js"></script>
<script src="../js/jquery/jquery.ui.sortable.js"></script>
<script src="../js/jquery/jquery.ui.accordion.js"></script>
<script src="../js/jquery/jquery.ui.datepicker.js"> </script>
<script src="../js/jquery/jquery.ui.position.js"></script>
<script src="../js/jquery/jquery.ui.autocomplete.js"></script>

<script src="../js/generico.js"></script>
<script src="../js/pedido/pedido.js"></script>


<style>
	
	#producto-label {
		display: block;
		font-weight: bold;
		margin-bottom: 1em;
	}
	#producto-icon {
		float: left;
		height: 32px;
		width: 32px;
	}
	#producto-description {
		margin: 0;
		padding: 0;
	}
    .Estilo2 {font-family: Verdana, Arial, sans-serif}
.Estilo4 {font-size: 14px}
    
</style>

	<script>

	  var ultimoTema= "";
	$(function() {
		var projects = [




<c:forEach var='producto'   items='${listaAlimentos}'>
{
	value: "${producto.nombreProducto}",
	label: "${producto.nombreProducto}",
	desc: "${producto.codigo}",
	icon: "jqueryui_32x32.png"
	},
</c:forEach>

	<c:forEach var='producto' items='${listaPiel}'>

	{
	value: "${producto.nombreProducto}",
	label: "${producto.nombreProducto}",
	desc: "${producto.codigo}",
	icon: "jqueryui_32x32.png"
	},

	</c:forEach>
	
	<c:forEach var='producto' items='${listaCapilar}'>

	{
	value: "${producto.nombreProducto}",
	label: "${producto.nombreProducto}",
	desc: "${producto.codigo}",
	icon: "jqueryui_32x32.png"
	},

	</c:forEach>

	<c:forEach var='producto' items='${listaAseoPersonal}'>

	{
	value: "${producto.nombreProducto}",
	label: "${producto.nombreProducto}",
	desc: "${producto.codigo}",
	icon: "jqueryui_32x32.png"
	},

	</c:forEach>
	
	<c:forEach var='producto' items='${listaFisioterapia}'>

	{
	value: "${producto.nombreProducto}",
	label: "${producto.nombreProducto}",
	desc: "${producto.codigo}",
	icon: "jqueryui_32x32.png"
	},

	</c:forEach>	
	<c:forEach var='producto' items='${listaAseoHogar}'>

	{
	value: "${producto.nombreProducto}",
	label: "${producto.nombreProducto}",
	desc: "${producto.codigo}",
	icon: "jqueryui_32x32.png"
	},

	</c:forEach>	
	<c:forEach var='producto' items='${listaVeterinaria}'>

	{
	value: "${producto.nombreProducto}",
	label: "${producto.nombreProducto}",
	desc: "${producto.codigo}",
	icon: "jqueryui_32x32.png"
	},

	</c:forEach>	

	<c:forEach var='producto' items='${listaExtractos}'>

	{
	value: "${producto.nombreProducto}",
	label: "${producto.nombreProducto}",
	desc: "${producto.codigo}",
	icon: "jqueryui_32x32.png"
	},

	</c:forEach>	
{
	value: "xx",
	label: "xx}",
	desc: "xx",
	icon: "jqueryui_32x32.png"
	}	                
	
		];

		$( "#producto" ).autocomplete({
			minLength: 0,
			source: projects,
			focus: function( event, ui ) {
				$( "#producto" ).val( ui.item.label );
				return false;
			},
			select: function( event, ui ) {

			 ultimoTema= ui.item.desc;
			 $("#"+ultimoTema).show();


				return false;
			}
		})
		.data( "autocomplete" )._renderItem = function( ul, item ) {




			return $( "<li></li>" )
				.data( "item.autocomplete", item )
				.append( "<a href='" + item.desc +"'>" + item.label+"</a>" )
				.appendTo( ul );
		};


		/**nuevo*/
		$("#producto").click(function () {


        /*  if(ultimoTema!=''){
		 	$("#"+ultimoTema).hide();


		 }*/
		 document.getElementById("producto").value='';
       // ultimoTema='';
  });



	});
	</script>


</head>
<body>

<div align="right" id="opcion">
		<a href="javascript:vistaPreliminarPedido();">Vista Preliminar del Pedido</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<form action="../pedido/VistaPreliminarPedido" method="post">
<div class="demo-description">
<div >

	  <p align="center"><strong> REALIZAR PEDIDO </strong></p>
	  
	  <table width="53%">

  <tr>
    <td width="27%">Nombre Empresario: </td>
    <td width="26%" ><input name="nombre" type="text" size="30" value="<c:out value="${afiliado.nombre}"/>" /></td>
    <td width="25%" >No. Identificaci&oacute;n:</td>
    <td width="22%" ><input name="documento" type="text" size="15" value="<c:out value="${afiliado.cedula}"/>" /></td>
  </tr>
  <tr>
    <td>Telefono:    </td>
    <td><input name="telefono" type="text" size="15" value="<c:out value="${afiliado.telefono}"/>" /></td>
    <td>Ciudad: </td>
    <td><input name="ciudad" type="text" size="20" value="<c:out value="${afiliado.ciudad}"/>" /></td>
   
  </tr>
   <input name="accion" type="hidden" size="20" value="<c:out value="${accion}"/>" />
  
</table>
	  
	  <p class="Estilo2"> Selecccione el producto para su pedido&nbsp;&nbsp; 

	<input id="producto" size="40"/>
	<input type="hidden" id="producto-id"/></p>
	<p id="producto-description"></p>
  </div>
<br/>
<br/>
<table>
  <tr class="tabla">
    <th width="10%">Codigo Producto</th>

    <th width="60%">Nombre Producto</th>
    <th width="10%">Precio</th>
    <th width="10%">Cantidad</th>
    <th width="10%">Seleccionar</th>

  </tr>
</table>
<br />
  <c:forEach var='producto'   items='${listaAlimentos}'>
  <div id="<c:out value='${producto.codigo}'/>"  style="display:none">
  <table>
  <tr>
    <td width="10%"><c:out value='${producto.codigo}'/></td>

    <td width="60%"><c:out value='${producto.nombreProducto}'/></td>
    <td width="10%"><c:out value='${producto.precioDistribuidor}'/></td>
    <td width="10%"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioDistribuidor}'/>_<c:out value='${producto.nombreProducto}'/>"  onclick="javascript:chequearProducto(this)"/></td>
    <td width="10%"><input type="checkbox" value="<c:out value='${producto.codigo}'/>" name="producto" />
    </td>
    </tr>
    </table>
    </div>
</c:forEach>


  <c:forEach var='producto'   items='${listaPiel}'>
  <div id="<c:out value='${producto.codigo}'/>"  style="display:none">
  <table>
  <tr>
    <td width="10%"><c:out value='${producto.codigo}'/></td>

    <td width="60%"><c:out value='${producto.nombreProducto}'/></td>
    <td width="10%"><c:out value='${producto.precioDistribuidor}'/></td>
    <td width="10%"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioDistribuidor}'/>_<c:out value='${producto.nombreProducto}'/>"  onclick="javascript:chequearProducto(this)"/></td>
    <td width="10%"><input type="checkbox" value="<c:out value='${producto.codigo}'/>" name="producto" />
    </td>
    </tr>
    </table>
    </div>
</c:forEach>

 <c:forEach var='producto'   items='${listaCapilar}'>
  <div id="<c:out value='${producto.codigo}'/>"  style="display:none">
  <table>
  <tr>
    <td width="10%"><c:out value='${producto.codigo}'/></td>

    <td width="60%"><c:out value='${producto.nombreProducto}'/></td>
    <td width="10%"><c:out value='${producto.precioDistribuidor}'/></td>
    <td width="10%"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioDistribuidor}'/>_<c:out value='${producto.nombreProducto}'/>"  onclick="javascript:chequearProducto(this)"/></td>
    <td width="10%"><input type="checkbox" value="<c:out value='${producto.codigo}'/>" name="producto" />
    </td>
    </tr>
    </table>
    </div>
</c:forEach>

<c:forEach var='producto'   items='${listaAseoPersonal}'>
  <div id="<c:out value='${producto.codigo}'/>"  style="display:none">
  <table>
  <tr>
    <td width="10%"><c:out value='${producto.codigo}'/></td>

    <td width="60%"><c:out value='${producto.nombreProducto}'/></td>
    <td width="10%"><c:out value='${producto.precioDistribuidor}'/></td>
    <td width="10%"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioDistribuidor}'/>_<c:out value='${producto.nombreProducto}'/>"  onclick="javascript:chequearProducto(this)"/></td>
    <td width="10%"><input type="checkbox" value="<c:out value='${producto.codigo}'/>" name="producto" />
    </td>
    </tr>
    </table>
    </div>
</c:forEach>

  <c:forEach var='producto'   items='${listaFisioterapia}'>
  <div id="<c:out value='${producto.codigo}'/>"  style="display:none">
  <table>
  <tr>
    <td width="10%"><c:out value='${producto.codigo}'/></td>
    <td width="60%"><c:out value='${producto.nombreProducto}'/></td>
    <td width="10%"><c:out value='${producto.precioDistribuidor}'/></td>
    <td width="10%"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioDistribuidor}'/>_<c:out value='${producto.nombreProducto}'/>"  onclick="javascript:chequearProducto(this)"/></td>
    <td width="10%"><input type="checkbox" value="<c:out value='${producto.codigo}'/>" name="producto" />
    </td>
    </tr>
    </table>
    </div>
</c:forEach>

<c:forEach var='producto'   items='${listaAseoHogar}'>
  <div id="<c:out value='${producto.codigo}'/>"  style="display:none">
  <table>
  <tr>
    <td width="10%"><c:out value='${producto.codigo}'/></td>
    <td width="60%"><c:out value='${producto.nombreProducto}'/></td>
    <td width="10%"><c:out value='${producto.precioDistribuidor}'/></td>
    <td width="10%"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioDistribuidor}'/>_<c:out value='${producto.nombreProducto}'/>"  onclick="javascript:chequearProducto(this)"/></td>
    <td width="10%"><input type="checkbox" value="<c:out value='${producto.codigo}'/>" name="producto" />
    </td>
    </tr>
    </table>
    </div>
</c:forEach>
<c:forEach var='producto'   items='${listaVeterinaria}'>
  <div id="<c:out value='${producto.codigo}'/>"  style="display:none">
  <table>
  <tr>
    <td width="10%"><c:out value='${producto.codigo}'/></td>
    <td width="60%"><c:out value='${producto.nombreProducto}'/></td>
    <td width="10%"><c:out value='${producto.precioDistribuidor}'/></td>
    <td width="10%"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioDistribuidor}'/>_<c:out value='${producto.nombreProducto}'/>"  onclick="javascript:chequearProducto(this)"/></td>
    <td width="10%"><input type="checkbox" value="<c:out value='${producto.codigo}'/>" name="producto" />
    </td>
    </tr>
    </table>
    </div>
</c:forEach>
<c:forEach var='producto'   items='${listaExtractos}'>
  <div id="<c:out value='${producto.codigo}'/>"  style="display:none">
  <table>
  <tr>
    <td width="10%"><c:out value='${producto.codigo}'/></td>
    <td width="60%"><c:out value='${producto.nombreProducto}'/></td>
    <td width="10%"><c:out value='${producto.precioDistribuidor}'/></td>
    <td width="10%"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioDistribuidor}'/>_<c:out value='${producto.nombreProducto}'/>"  onclick="javascript:chequearProducto(this)"/></td>
    <td width="10%"><input type="checkbox" value="<c:out value='${producto.codigo}'/>" name="producto" />
    </td>
    </tr>
    </table>
    </div>
</c:forEach>


  </div>
</form>
</body>
</html>
