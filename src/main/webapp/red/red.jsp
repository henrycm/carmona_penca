<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>APLICACION MULTIALOE</title>
<title>jQuery UI Accordion - Default functionality</title>
<link rel="stylesheet" href="../../css/jquery/jquery.ui.all.css">
<script src="../../js/jquery/jquery-1.5.1.js"></script>
<script src="../../js/jquery/jquery.ui.core.js"></script>
<script src="../../js/jquery/jquery.ui.widget.js"></script>
<script src="../../js/jquery/jquery.ui.accordion.js"></script>
<script src="../../js/jquery/jquery.effects.core.js"></script>
<script src="../../js/jquery/jquery.effects.drop.js"></script>
<script src="../../js/jquery/jquery.ui.datepicker.js"></script>
<script src="../../js/red/red.js"></script>
<script src="../../js/afiliado/afiliado.js"></script>
<script src="../../js/generico.js"></script>

<link rel="stylesheet" href="../../css/jquery/demos.css">

<link rel="stylesheet" href="../../css/multinivel.css">
<script>
	$(function() {
		$("#accordion").accordion();
	});
</script>
<script>
	$(function() {
		$("#fecha").datepicker({
			maxDate : '-18y'
		});
		$("#fecha").datepicker("option", "showAnim", "drop");

	});
</script>
<style>
#accordion {
	height: 100px;
}
</style>

</head>
<body>
	<div align="center">
		<form name="forma" action="IndexRed" method="post">
			<div align="right" id="opcion">
				<a href="javascript:inscribirRed();">Ingresar Red</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<div align="left" id="datosRed">

				Código de la red: <input name="red" type="text" size="4"
					maxlength="4" /> Nombre de la red: <input name="nombreRed"
					type="text" size="25" maxlength="50" />
			</div>
			<br>
			<div align="center">FORMULARIO DE INSCRIPCIÓN E INFORMACIÓN
				COMERCIAL DEL PRIMER AFILIADO DE LA RED</div>
			<div align="left">
				<input name="accion" type="hidden"
					value="<c:out value='${accion}'/>" /> Código Nuevo Empresario: <input
					name="codigoEmpresario" type="text" size="11" maxlength="11" /><select
					name="letra">
					<option value="A">A</option>
					<!--           <option value="B" >B</option>
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
-->
				</select> Tipo de Afiliado: <select name="rol">
					<option value="3">Afiliado</option>
					<option value="2">Distribuidor</option>
				</select>
			</div>
			<div class="demo">

				<div id="accordion">
					<h3>
						<a href="#">DATOS PERSONALES</a>
					</h3>
					<div id="datosPersonales">
						<table width="100%">
							<tr align="left">
								<td width="20%">Nombre: <input name="nombre" type="text"
									size="20" maxlength="15" /></td>
								<td width="25%">Apellidos Completos: <input name="apellido"
									type="text" size="15" maxlength="24" /></td>
								<td width="22%">Fecha de Nac: <input name="fechaNacimiento"
									type="text" class="ui-datepicker-calendar" id="fecha" size="10"
									maxlength="10" /></td>
								<td colspan="2">Identificación:<br> <select
									name="tipoDocumento">
										<option value="C.C.">C.C.</option>
										<option value="Nit">Nit</option>
								</select> <input name="numeroIdentificacion" type="text" size="10"
									maxlength="13" /> de <input name="de" type="text" size="15" />
								</td>
							</tr>
							<tr align="left">
								<td>Lugar de Nac.: Ciudad <input name="ciudadNacimiento"
									type="text" size="13" maxlength="25" />
								</td>
								<td height="26">Dpto <select id="departamento"
									name="departamento">
										<option value="">Elija el depart...</option>
										<c:forEach var='dept' items='${listaDepartamentos}'>
											<option value="<c:out value='${dept.descripcion}'/>"><c:out
													value='${dept.descripcion}' /></option>
										</c:forEach>
								</select>
								</td>
								<td>Profesion u Ocupación:<input name="profesion"
									type="text" size="15" maxlength="20" /></td>
								<td width="18%">Estado Civil: <select name="estadoCivil">
										<option value="casado">Casado</option>
										<option value="soltero">Soltero</option>
										<option value="union Libre">Unión Libre</option>
										<option value="Viudo">Viudo</option>
										<option value="Viudo">Separado</option>
								</select>
								</td>
								<td width="21%" height="24" align="center">Nombre del
									Conyugue/Tutor: <input name="nombreConyugue" type="text"
									size="15" maxlength="50" />
								</td>
							</tr>
							<tr align="left">
								<td align="left">C.c: <input name="documentoConyugue"
									type="text" size="20" maxlength="13" /></td>
								<td height="24" colspan="2">Dirección Resid. (completa): <input
									name="direccion" type="text" size="15" maxlength="60" />
								</td>
								<td>Barrio: <input name="barrio" type="text" size="25"
									maxlength="50" /></td>

								<td>Ciudad: <input name="ciudadResidencia" type="text"
									size="15" maxlength="20" /></td>
							</tr>
							<tr align="left">
								<td height="51">Departamento: <select
									id="departamentoResidencia" name="departamentoResidencia"
									size="1">
										<option value="">Elija el depart...</option>
										<c:forEach var='dept' items='${listaDepartamentos}'>
											<option value="<c:out value='${dept.descripcion}'/>"><c:out
													value='${dept.descripcion}' /></option>
										</c:forEach>
								</select></td>
								<td>Teléfono: <input name="telefono" type="text" size="10"
									maxlength="10" /></td>
								<td colspan="3">Celular o Beeper: <input name="celular"
									type="text" size="10" maxlength="10" /> E-mail: <input
									name="email" type="text" size="20" maxlength="50" /></td>
							</tr>
						</table>
					</div>
				</div>
				<h3>
					<a href="#">DATOS PARA PAGO DE RECONOCIMIENTOS MONETARIOS.</a>
				</h3>
				<div>
					<table width="100%">
						<tr>
							<td colspan="2">PAGAR A DISTRIBUIDOR <input
								name="pagarADistribuidor" type="checkbox"
								onclick="javascript:validarChequeCta()" />
							</td>
						</tr>
						<tr>
							<td width="50%">Cuenta Número: <input name="numeroCuenta"
								type="text" size="20" maxlength="14" />
							</td>
							<td width="50%">Entidad:<select id="entidadBancaria"
								name="entidadBancaria">
									<option value="">Elija el banco</option>
									<c:forEach var='banco' items='${listaBancos}'>
										<option value="<c:out value='${banco.codigo}'/>"><c:out
												value='${banco.descripcion}' /></option>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<td>Tipo de cta. Ahorro <input name="tipoCuenta"
								type="radio" value="AHORRO" checked="checked" /> Cte. <input
								name="tipoCuenta" type="radio" value="CRTE" />
							</td>
							<td>Ciudad: <input name="ciudadCuenta" type="text" size="10"
								maxlength="20" />
							</td>
						</tr>
						<tr>
							<td colspan="2">Titular de la cuenta (Si es diferente al
								empresario)</td>
						</tr>
						<tr>
							<td>Nombre: <input name="nombreTitular" type="text"
								size="30" maxlength="20" />
							</td>
							<td>C.C. No.<input name="documentoTitular" type="text"
								size="15" maxlength="15" /></td>
						</tr>
						<tr>
							<td colspan="2">Autorizo que las bonificaciones generadas en
								mi red de ALOE de COLOMBIA sean consignadas en esta cuenta.</td>
						</tr>
					</table>
				</div>
				<h3>
					<a href="#">DATOS DEL DISTRIBUIDOR</a>
				</h3>
				<div id="datosPatrocinador">
					<input name="nombrePatrocinador" type="hidden" /> <input
						name="numeroEmpresario" type="hidden" />
					<table width="917" align="left">
						<tr>
							<td align="left">Nombres y Apellidos del distribuidor <input
								name="nombreDistribuidor" type="text" size="50" maxlength="50"
								value="<c:out value='${usuario.nombre}'/> <c:out value='${usuario.apellido}'/>"
								readonly="readonly"
								onclick="javascript:document.forma.nombrePatrocinador.readOnly =true;" />
							</td>
						</tr>
						<tr>
							<td align="left">Codigo de Empresario(Distribuidor): <input
								name="numeroDistribuidor"
								value="<c:out value='${usuario.cedula}'/>" type="text" size="15"
								maxlength="13" readonly="readonly"
								onclick="javascript:document.forma.numeroEmpresario.readOnly =true;" />
								<a href="javascript:abrirBuscarDistribuidor()">Buscar
									Distribuidor</a> <input type="hidden"
								name="numeroCuentaDistribuidor"
								value="<c:out value='${usuario.cuentaNro}'/>"> <input
								type="hidden" name="tipoCuentaDistribuidor"
								value="<c:out value='${usuario.tipoCuenta}'/>"> <input
								type="hidden" name="ciudadCuentaDistribuidor"
								value="<c:out value='${usuario.ciudadCta}'/>"> <input
								type="hidden" name="bancoDistribuidor"
								value="<c:out value='${usuario.banco}'/>">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
