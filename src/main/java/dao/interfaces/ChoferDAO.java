package dao.interfaces;


import java.util.List;

import dominio.Chofer;

public interface ChoferDAO {

	public void agregarChofer(Chofer c) throws Exception;
	public int obtenerUltId() throws Exception;
	public List <Chofer> cargarTodoChoferes() throws Exception;
	public void eliminarChofer(int id) throws Exception;
	public Chofer buscarChofer(int id) throws Exception;
	public void updateChofer(Chofer c) throws Exception;
	public int buscarChoferDNI(long dni) throws Exception;
}
