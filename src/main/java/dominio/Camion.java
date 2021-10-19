package dominio;

import java.util.List;

public class Camion {
private int idCamion;
private String marca;
private String modelo;
private String dominio;
private double carga;
private double tanque;
private double consumo;
private Viaje viaje;

public Camion() {}

public Camion(int idCamion, String marca, String modelo, String dominio, double capacidadToneladas, 
		double cantLitrosNafta, double cantLitrosConsumidosKM, Viaje viaje) {
	this.idCamion = idCamion; this.marca = marca; this.modelo = modelo; this.dominio = dominio;
	this.carga = capacidadToneladas; this.tanque = cantLitrosNafta;
	this.consumo = cantLitrosConsumidosKM; this.viaje = viaje;
}

public int getIdCamion() {
	return idCamion;
}
public void setIdCamion(int idCamion) {
	this.idCamion = idCamion;
}
public String getMarca() {
	return marca;
}
public void setMarca(String marca) {
	this.marca = marca;
}
public String getModelo() {
	return modelo;
}
public void setModelo(String modelo) {
	this.modelo = modelo;
}
public String getDominio() {
	return dominio;
}
public void setDominio(String dominio) {
	this.dominio = dominio;
}
public double getCarga() {
	return carga;
}
public void setCarga(double capacidadToneladas) {
	this.carga = capacidadToneladas;
}
public double getTanque() {
	return tanque;
}
public void setTanque(double cantLitrosNafta) {
	this.tanque = cantLitrosNafta;
}
public double getConsumo() {
	return consumo;
}
public void setConsumo(double cantLitrosConsumidosKM) {
	this.consumo = cantLitrosConsumidosKM;
}

public Viaje getViaje() {
	return viaje;
}

public void setViaje(Viaje viaje) {
	this.viaje = viaje;
}

}
