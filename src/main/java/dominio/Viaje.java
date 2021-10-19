package dominio;

import java.util.Calendar;
import java.util.List;

public class Viaje {
private int idViaje;
private String destino;
private String origen;
private boolean inicio; //El camionero puede editar este dato
private boolean finalizado; //El camionero puede editar este dato
private double tiempoViaje; //Expresado en dias
private double distancia;

public Viaje() {}

public Viaje(int idViaje, String destino, String origen, boolean inicio, boolean finalizado, double tiempoViaje,
		double distancia) {
	this.idViaje = idViaje; this.destino = destino; this.origen = origen; this.inicio = inicio; this.finalizado = finalizado;
	this.tiempoViaje = tiempoViaje; this.distancia = distancia;
}

public String getDestino() {
	return destino;
}
public void setDestino(String destino) {
	this.destino = destino;
}
public String getOrigen() {
	return origen;
}
public void setOrigen(String origen) {
	this.origen = origen;
}
public boolean isInicio() {
	return inicio;
}
public void setInicio(boolean inicio) {
	this.inicio = inicio;
}
public boolean isFinalizado() {
	return finalizado;
}
public void setFinalizado(boolean finalizado) {
	this.finalizado = finalizado;
}
public double getTiempoViaje() {
	return tiempoViaje;
}
public void setTiempoViaje(double tiempoViaje) {
	this.tiempoViaje = tiempoViaje;
}
public double getDistancia() {
	return distancia;
}
public void setDistancia(double distancia) {
	this.distancia = distancia;
}

public int getIdViaje() {
	return idViaje;
}

public void setIdViaje(int idViaje) {
	this.idViaje = idViaje;
}

}
