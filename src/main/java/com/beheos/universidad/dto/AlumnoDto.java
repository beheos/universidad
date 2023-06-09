package com.beheos.universidad.dto;


public class AlumnoDto{

	private Long id;
	private LicenciaturaDto licenciatura;
	private String nombre;
	private String paterno;
	private String materno;
	private String correo;
	private String fecha_ingreso;
	private String fecha_modificacion;
	private String usuarioIngreso;
	private String usuarioModifico;
	private String matricula;
	private Boolean eliminado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LicenciaturaDto getLicenciatura() {
		return licenciatura;
	}
	public void setLicenciatura(LicenciaturaDto licenciatura) {
		this.licenciatura = licenciatura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public String getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(String fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public String getUsuarioIngreso() {
		return usuarioIngreso;
	}
	public void setUsuarioIngreso(String usuarioIngreso) {
		this.usuarioIngreso = usuarioIngreso;
	}
	public String getUsuarioModifico() {
		return usuarioModifico;
	}
	public void setUsuarioModifico(String usuarioModifico) {
		this.usuarioModifico = usuarioModifico;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	
}
