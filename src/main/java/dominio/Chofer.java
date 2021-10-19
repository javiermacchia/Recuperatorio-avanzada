package dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Chofer extends Usuario{

private String nombre;
private String apellido;
private long dni;
private Calendar fechaNac;
private String categoria;
private int movil;
private List <Viaje> listaViaje = new ArrayList <Viaje>();

public Chofer() {
	super();
}

public Chofer(long idUsuario, String clave, String nombreUsuario, String perfil, String nombre,
		String apellido, long dni, Calendar fechaNac, String categoria, int movil, List <Viaje> listaViaje) {
	super(idUsuario, clave, nombreUsuario, perfil);
	this.nombre = nombre; this.apellido = apellido; this.dni = dni;this.fechaNac = fechaNac;
	this.categoria = categoria; this.movil = movil; this.listaViaje = listaViaje;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellido() {
	return apellido;
}

public void setApellido(String apellido) {
	this.apellido = apellido;
}

public long getDni() {
	return dni;
}

public void setDni(long dni) {
	this.dni = dni;
}

public Calendar getFechaNac() {
	return fechaNac;
}

public void setFechaNac(Calendar fechaNac) {
	this.fechaNac = fechaNac;
}

public String getCategoria() {
	return categoria;
}

public void setCategoria(String categoria) {
	this.categoria = categoria;
}

public int getMovil() {
	return movil;
}

public void setMovil(int movil) {
	this.movil = movil;
}

public List <Viaje> getListaViaje() {
	return listaViaje;
}

public void setListaViaje(List <Viaje> listaViaje) {
	this.listaViaje = listaViaje;
}


}
