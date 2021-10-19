package dao.factory;

import dao.impl.UsuarioImpl;
import dao.interfaces.UsuarioDAO;

public class UsuarioFactory {

	
	public static UsuarioDAO getImplementation(String source) {
		if (source.equals("BD")) {
			return new UsuarioImpl();
		}

		return null;
	}
	
}
