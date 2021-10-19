package dao.interfaces;


import dominio.Usuario;

public interface UsuarioDAO {
	
	public Usuario buscarUsuario(String nombreUsuario, String clave) throws Exception;
	public Usuario buscarUsuarioID(int id) throws Exception;
	public int obtenerUltId() throws Exception;
	void agregarUsuario(String nombreUsuario, String clave, int id) throws Exception;
	public void eliminarUsuario(int id) throws Exception;
	public void updateUsuario(Usuario u) throws Exception;
}