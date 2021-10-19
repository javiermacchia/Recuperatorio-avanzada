package dao.interfaces;


import java.util.List;

import dominio.Camion;

public interface CamionDAO {

	public int obtenerUltId() throws Exception;
	public void agregarCamion(Camion camion) throws Exception;
	List <Camion> cargarCamionesSinViaje() throws Exception;
	public void eliminarCamion(int id) throws Exception;
	public void updateCamion(Camion c) throws Exception;
	public Camion buscarCamion(int id) throws Exception;
	public void updateViajeCamion(int idCamion, int idViaje) throws Exception;
	public int buscarCamionPatente(String patente) throws Exception;
	
}
