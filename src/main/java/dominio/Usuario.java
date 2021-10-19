package dominio;

public class Usuario {
protected long idUsuario;
protected String clave;
protected String nombreUsuario;
protected String perfil;

public Usuario() {}

public Usuario(long idUsuario, String clave, String nombreUsuario, String perfil) {
	this.idUsuario = idUsuario; this.clave = clave; this.nombreUsuario = nombreUsuario; this.perfil = perfil;
}

public long getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(long idUsuario) {
	this.idUsuario = idUsuario;
}
public String getClave() {
	return clave;
}
public void setClave(String clave) {
	this.clave = clave;
}
public String getNombreUsuario() {
	return nombreUsuario;
}
public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
}
public String getPerfil() {
	return perfil;
}
public void setPerfil(String perfil) {
	this.perfil = perfil;
}
	
	
}
