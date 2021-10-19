package dao.factory;


import dao.impl.ViajeImpl;
import dao.interfaces.ViajeDAO;

public class ViajeFactory {

	public static ViajeDAO getImplementation(String source) {
		if (source.equals("BD")) {
			return new ViajeImpl();
		}
		return null;
	}
}
