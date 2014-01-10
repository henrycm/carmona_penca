<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Documento sin t&iacute;tulo</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
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
	 <script src="../js/generico.js" ></script>
	 <script src="../js/pedido/pedido.js" ></script>
<link rel="stylesheet" href="../css/multinivel.css">
 
 
<link rel="stylesheet" href="../css/jquery/demos.css">
	<style>
	h1 { padding: .1em; margin: 0; font-size:18px; }
	#products { float:left; width:500px; margin-right: 2em;  border-color:#006600;}
	#productosAComprar { width: 400px; float: left;  }
	/* style the list to maximize the droppable hitarea */
	#productosAComprar ol { margin: 0; padding: 1em 0 1em 3em; }
.Estilo12 {font-family: Verdana, Arial, sans-serif}
    </style>
	<script>
	$(function() {
		$( "#catalog" ).accordion();
		$( "#catalog li" ).draggable({
			appendTo: "body",
			helper: "clone"
		});
		$( "#productosAComprar ol" ).droppable({
			activeClass: "ui-state-default",
			hoverClass: "ui-state-hover",
			accept: ":not(.ui-sortable-helper)",
			drop: function( event, ui ) {
				$( this ).find( ".placeholder" ).remove();
 
			    $('<li></li>').html(ui.draggable.html()).appendTo(this);
 
			}
		}).sortable({
			items: "li:not(.placeholder)",
			sort: function() {
				// gets added unintentionally by droppable interacting with sortable
				// using connectWithSortable fixes this, but doesn't allow you to customize active/hoverClass options
				$( this ).removeClass( "ui-state-default" );
			}
		});
	});
	</script>
	<script>
	$(function() {
		$( "#fecha" ).datepicker();
		$( "#fecha" ).datepicker( "option", "showAnim", "drop" );
 
	});
	</script>
 
   
 
</head>
<body>
<p>&nbsp;</p>
<table width="53%">


  <tr>
    <td width="27%">Nombre Empresario: </td>
    <td width="26%" ><input name="NombreEmpresario" type="text" size="20" /></td>
    <td width="25%" >No. Identificaci&oacute;n:</td>
    <td width="22%" ><input name="NumeroIdentificacion" type="text" size="15" /></td>
  </tr>
  <tr>
    <td>Telefono:    </td>
    <td><input name="NumeroIdentificacion2" type="text" size="15" /></td>
    <td>Ciudad: </td>
    <td><input name="CiudadEmpre" type="text" size="15" /></td>
  </tr>
  <tr>
    <td >Fecha:</td>
    <td>
     <input name="fecha" type="text" class="ui-datepicker-calendar" id="fecha" size="15" />
    </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<div class="demo">
 
<div id="products">
	<h1 class="ui-widget-header"> Productos</h1>	
	<div id="catalog">
		<h3><a href="#">Linea de Alimentos </a></h3>
		<div>
			<ul>
				<c:forEach var='producto'   items='${listaAlimentos}'>
					<li><c:out value='${producto.nombreProducto}'/> - Precio:<c:out value='${producto.precioAfiliado}'/> <span align="rigth"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioAfiliado}'/>" type="text" size="5" maxlength=2/> </Span></li> 
				</c:forEach>
				
			</ul>
		</div>
		<h3><a href="#">L&iacute;nea para la Piel </a></h3>
		<div>
			<ul>
				<c:forEach var='producto1'   items='${listaPiel}'>
					<li><c:out value='${producto1.nombreProducto}'/> - Precio:<c:out value='${producto1.precioAfiliado}'/> <span align="rigth"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioAfiliado}'/>" type="text" size="5" maxlength=2/> </Span></li> 
				</c:forEach>
			</ul>
		</div>
		<h3><a href="#">Linea Capilar </a></h3>
		<div>
			<ul>
				<c:forEach var='producto2'   items='${listaCapilar}'>
					<li><c:out value='${producto2.nombreProducto}'/> - Precio:<c:out value='${producto2.precioAfiliado}'/> <span align="rigth"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioAfiliado}'/>" type="text" size="5" maxlength=2/> </Span></li> 
				</c:forEach>
			</ul>
		</div>
		<h3><a href="#">Linea de Aseo Personal  </a></h3>
		<div>
			<ul>
				<c:forEach var='producto'   items='${listaAseoPersonal}'>
					<li><c:out value='${producto.nombreProducto}'/> - Precio:<c:out value='${producto.precioAfiliado}'/> <span align="rigth"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioAfiliado}'/>" type="text" size="5" maxlength=2/> </Span></li> 
				</c:forEach>
			</ul>
		</div>
		<h3><a href="#">Linea Fisioterapia y Estética  </a></h3>
		<div>
			<ul>
				<c:forEach var='producto'   items='${listaFisioterapia}'>
					<li><c:out value='${producto.nombreProducto}'/> - Precio:<c:out value='${producto.precioAfiliado}'/> <span align="rigth"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioAfiliado}'/>" type="text" size="5" maxlength=2/> </Span></li> 
				</c:forEach>
			</ul>
		</div>
		<h3><a href="#">Linea Aseo del Hogar  </a></h3>
		<div>
			<ul>
				<c:forEach var='producto'   items='${listaAseoHogar}'>
					<li><c:out value='${producto.nombreProducto}'/> - Precio:<c:out value='${producto.precioAfiliado}'/> <span align="rigth"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioAfiliado}'/>" type="text" size="5" maxlength=2/> </Span></li> 
				</c:forEach>
			</ul>
		</div>
		<h3><a href="#">Linea Veterinaria </a></h3>
		<div>
			<ul>
				<c:forEach var='producto'   items='${listaVeterinaria}'>
					<li><c:out value='${producto.nombreProducto}'/> - Precio:<c:out value='${producto.precioAfiliado}'/> <span align="rigth"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioAfiliado}'/>" type="text" size="5" maxlength=2/> </Span></li> 
				</c:forEach>
				
			</ul>
		</div>
		<h3><a href="#">Extractos </a></h3>
		<div>
			<ul>
				<c:forEach var='producto'   items='${listaExtractos}'>
					<li><c:out value='${producto.nombreProducto}'/> - Precio:<c:out value='${producto.precioAfiliado}'/> <span align="rigth"><input name="cantidad_<c:out value='${producto.codigo}'/>_<c:out value='${producto.precioAfiliado}'/>" type="text" size="5" maxlength=2/> </Span></li> 
				</c:forEach>
				
			</ul>
		</div>		
	</div>
</div>
             <form action="../ControlPedido" method="post" name="pedido" id="pedido">
<div id="productosAComprar">
	<h1 class="ui-widget-header">Pedidos</h1>
	<div class="ui-widget-content">
 
 
		<ol>
              <li class="placeholder">Seleccione el producto a comprar</li>
 
		</ol>
 
 
 
 
	</div>
	 TOTAL PEDIDO:<input type="text" name="totalPedido" value=0>
	<input type="hidden" name="accion" value="c">
	<a href="javascript:calcularPedido();">
    	<a href="javascript:calcularPedido();" > Calcular Pedido</a>
    	<br/>
    	<a href="javascript:borrarPedido();" >Borrar Pedido</a>
    	<br/>
    	<a href="javascript:enviarPedido();" >Enviar Pedido</a>
    
     </form>
</div>
 
</div><!-- End demo -->
 
 
 
<div class="demo-description">
 
</div><!-- End demo-description -->
 
</body>
</html>
