package dao.interfaces;

import java.util.List;

import dominio.Viaje;

public interface ViajeDAO {
	public int obtenerUltId() throws Exception;
	public void agregarViaje(Viaje v, long l, int idCamion) throws Exception;
	public List <Viaje> buscarViajesChofer(long l) throws Exception;
	public void eliminarViaje(int id) throws Exception;
	public int buscarViajeCamion(int idViaje) throws Exception;
	public Viaje buscarViaje(int idViaje) throws Exception;
	public void updateViaje(Viaje v, long l, int idCamion) throws Exception;
	public int buscarViajeChoferID(int idViaje) throws Exception;
}
