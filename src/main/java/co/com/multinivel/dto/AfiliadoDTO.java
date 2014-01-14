package co.com.multinivel.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class AfiliadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cedula;
	private String activo;
	private String apellido;
	private String banco;
	private String barrio;
	private String cedulaHijo1;
	private String cedulaHijo2;
	private String cedulaHijo3;
	@Column(name = "cedula_papa")
	private String cedulaPapa;
	private String celular;
	private String ciudad;
	private String ciudadResidencia;
	private String cuentaNro;
	private int cuota;
	private String departamento;
	private String departamentoResidencia;
	private String direccion;
	private String documentoHeredero1;
	private String documentoHeredero2;
	private String eMail;
	private String estadoCivil;
	private String fechaNacimiento;
	private String fechaNacHeredero2;
	private String fechaNacHeredero1;
	private String documentoConyugue;
	private String nombre;
	private String nombreConyugue;
	private String nombreHerencia1;
	private String nombreHerencia2;
	private String parentesco1;
	private String parentesco2;
	private String nombrePadre;
	private String nombreTitularCta;
	private String ocupacion;
	private int porcentaje1;
	private int porcentaje2;
	private String red;
	private int telefono;
	private String tipoCuenta;
	private String tipoDocumento;
	private String tipoDocumentoConyugue;
	private String tipoDocumentoHeredero1;
	private String tipoDocumentoHeredero2;
	private String titularCuenta;
	private String vinculadoPor;
	private String cedulaDistribuidor;
	private String nombreDistribuidor;
	private int numeroAfiliados;

	public String getCedulaDistribuidor() {
		return this.cedulaDistribuidor;
	}

	public void setCedulaDistribuidor(String cedulaDistribuidor) {
		this.cedulaDistribuidor = cedulaDistribuidor;
	}

	public String getNombreDistribuidor() {
		return this.nombreDistribuidor;
	}

	public void setNombreDistribuidor(String nombreDistribuidor) {
		this.nombreDistribuidor = nombreDistribuidor;
	}

	public int getNumeroAfiliados() {
		return this.numeroAfiliados;
	}

	public void setNumeroAfiliados(int numeroAfiliados) {
		this.numeroAfiliados = numeroAfiliados;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getBarrio() {
		return this.barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCedulaHijo1() {
		return this.cedulaHijo1;
	}

	public void setCedulaHijo1(String cedulaHijo1) {
		this.cedulaHijo1 = cedulaHijo1;
	}

	public String getCedulaHijo2() {
		return this.cedulaHijo2;
	}

	public void setCedulaHijo2(String cedulaHijo2) {
		this.cedulaHijo2 = cedulaHijo2;
	}

	public String getCedulaHijo3() {
		return this.cedulaHijo3;
	}

	public void setCedulaHijo3(String cedulaHijo3) {
		this.cedulaHijo3 = cedulaHijo3;
	}

	public String getCedulaPapa() {
		return this.cedulaPapa;
	}

	public void setCedulaPapa(String cedulaPapa) {
		this.cedulaPapa = cedulaPapa;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCiudadResidencia() {
		return this.ciudadResidencia;
	}

	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}

	public String getCuentaNro() {
		return this.cuentaNro;
	}

	public void setCuentaNro(String cuentaNro) {
		this.cuentaNro = cuentaNro;
	}

	public int getCuota() {
		return this.cuota;
	}

	public void setCuota(int cuota) {
		this.cuota = cuota;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDepartamentoResidencia() {
		return this.departamentoResidencia;
	}

	public void setDepartamentoResidencia(String departamentoResidencia) {
		this.departamentoResidencia = departamentoResidencia;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDocumentoHeredero1() {
		return this.documentoHeredero1;
	}

	public void setDocumentoHeredero1(String documentoHeredero1) {
		this.documentoHeredero1 = documentoHeredero1;
	}

	public String getDocumentoHeredero2() {
		return this.documentoHeredero2;
	}

	public void setDocumentoHeredero2(String documentoHeredero2) {
		this.documentoHeredero2 = documentoHeredero2;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaNacHeredero2() {
		return this.fechaNacHeredero2;
	}

	public void setFechaNacHeredero2(String fechaNacHeredero2) {
		this.fechaNacHeredero2 = fechaNacHeredero2;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreConyugue() {
		return this.nombreConyugue;
	}

	public void setNombreConyugue(String nombreConyugue) {
		this.nombreConyugue = nombreConyugue;
	}

	public String getNombreHerencia1() {
		return this.nombreHerencia1;
	}

	public void setNombreHerencia1(String nombreHerencia1) {
		this.nombreHerencia1 = nombreHerencia1;
	}

	public String getNombrePadre() {
		return this.nombrePadre;
	}

	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}

	public String getNombreTitularCta() {
		return this.nombreTitularCta;
	}

	public void setNombreTitularCta(String nombreTitularCta) {
		this.nombreTitularCta = nombreTitularCta;
	}

	public String getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public int getPorcentaje1() {
		return this.porcentaje1;
	}

	public void setPorcentaje1(int porcentaje1) {
		this.porcentaje1 = porcentaje1;
	}

	public int getPorcentaje2() {
		return this.porcentaje2;
	}

	public void setPorcentaje2(int porcentaje2) {
		this.porcentaje2 = porcentaje2;
	}

	public String getRed() {
		return this.red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getTipoCuenta() {
		return this.tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoDocumentoConyugue() {
		return this.tipoDocumentoConyugue;
	}

	public void setTipoDocumentoConyugue(String tipoDocumentoConyugue) {
		this.tipoDocumentoConyugue = tipoDocumentoConyugue;
	}

	public String getTipoDocumentoHeredero1() {
		return this.tipoDocumentoHeredero1;
	}

	public void setTipoDocumentoHeredero1(String tipoDocumentoHeredero1) {
		this.tipoDocumentoHeredero1 = tipoDocumentoHeredero1;
	}

	public String getTipoDocumentoHeredero2() {
		return this.tipoDocumentoHeredero2;
	}

	public void setTipoDocumentoHeredero2(String tipoDocumentoHeredero2) {
		this.tipoDocumentoHeredero2 = tipoDocumentoHeredero2;
	}

	public String getTitularCuenta() {
		return this.titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}

	public String getVinculadoPor() {
		return this.vinculadoPor;
	}

	public void setVinculadoPor(String vinculadoPor) {
		this.vinculadoPor = vinculadoPor;
	}

	public void setNombreHerencia2(String nombreHerencia2) {
		this.nombreHerencia2 = nombreHerencia2;
	}

	public String getNombreHerencia2() {
		return this.nombreHerencia2;
	}

	public void setParentesco1(String parentesco1) {
		this.parentesco1 = parentesco1;
	}

	public String getParentesco1() {
		return this.parentesco1;
	}

	public void setParentesco2(String parentesco2) {
		this.parentesco2 = parentesco2;
	}

	public String getParentesco2() {
		return this.parentesco2;
	}

	public void setFechaNacHeredero1(String fechaNacHeredero1) {
		this.fechaNacHeredero1 = fechaNacHeredero1;
	}

	public String getFechaNacHeredero1() {
		return this.fechaNacHeredero1;
	}

	public void setDocumentoConyugue(String documentoConyugue) {
		this.documentoConyugue = documentoConyugue;
	}

	public String getDocumentoConyugue() {
		return this.documentoConyugue;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getApellido() {
		return this.apellido;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dto.AfiliadoDTO
 */